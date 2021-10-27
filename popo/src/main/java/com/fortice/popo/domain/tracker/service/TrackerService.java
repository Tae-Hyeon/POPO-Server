package com.fortice.popo.domain.tracker.service;

import com.fortice.popo.domain.model.*;
import com.fortice.popo.domain.popo.repository.OptionRepository;
import com.fortice.popo.domain.popo.repository.PopoRepository;
import com.fortice.popo.domain.tracker.repository.TrackerContentRepository;
import com.fortice.popo.domain.tracker.repository.TrackerRepository;
import com.fortice.popo.domain.tracker.dto.*;
import com.fortice.popo.global.common.GlobalValue;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import com.fortice.popo.global.util.Formatter;
import com.fortice.popo.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TrackerService {
    private final PopoRepository popoRepository;
    private final OptionRepository optionRepository;
    private final TrackerRepository trackerRepository;
    private final TrackerContentRepository trackerContentRepository;

    public TrackerResponse getTracker(Integer popoId, String year, String month) throws Exception {
        Popo popo = popoRepository.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        Checker.checkPermission(popo, SecurityUtil.getCurrentUserId());

        String dateFormat = Formatter.getDateFormatByYearAndMonth(year, month);
        List<DayDTO> tracker = trackerRepository.getDayDTOById(popoId, dateFormat);
        for (DayDTO day : tracker)
            day.setUri(GlobalValue.getImageServerURI());
        String background_url = popo.getTracker_image().isEmpty() ? "" : GlobalValue.getImageServerURI() + popo.getTracker_image();
        TrackerResponse trackerResponse = TrackerResponse.builder()
                .category(popo.getCategory())
                .background(background_url)
                .build();
        trackerResponse.updateTracker(year, month, tracker);

        return trackerResponse;
    }

    public DayResponse getOneDay(Integer popoId, Integer dayId) throws Exception {
        List<OptionContentDTO> options = trackerContentRepository.findOptionsByDayId(dayId);
        Checker.checkEmpty(options);

        DayResponse dayResponse = trackerRepository.getDayResponseById(dayId);

        dayResponse.setUri(GlobalValue.getImageServerURI());
        dayResponse.setOptions(options);

        return dayResponse;
    }

    public String deleteOneDay(Integer dayId) throws Exception {
        Day day = trackerRepository.findByDayId(dayId)
                .orElseThrow(NotFoundDataException::new);
        Checker.checkPermission(day, SecurityUtil.getCurrentUserId());
        trackerRepository.delete(day);
        return "";
    }

    public Integer insertOneDay(CreateDayRequest request) throws Exception {
        String path = FileUtil.uploadFile(request.getImage(), "day", 0);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate());
        Day newDay = Day.builder()
                .popo(Popo.builder().id(request.getPopoId()).build())
                .date(date)
                .image(path)
                .build();

        newDay = trackerRepository.save(newDay);

        List<Option> options = optionRepository.getOptionByPopo(request.getPopoId());
        List<OptionContent> newContents = new ArrayList<>();
        for (Option option : options) {
            OptionContent newContent = OptionContent.builder()
                    .option(option)
                    .day(newDay)
                    .contents(request.getContentsByOptionId(option.getId()))
                    .build();

            newContents.add(newContent);
        }

        trackerContentRepository.saveAll(newContents);

        return newDay.getId();
    }

    public String patchContents(Integer contentId, String contents) throws Exception {
        OptionContent content = trackerContentRepository.findById(contentId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkPermission(content, SecurityUtil.getCurrentUserId());

        content.setContents(contents);
        trackerContentRepository.save(content);

        return "";
    }

    public String patchImage(Integer dayId, MultipartFile image) throws Exception {
        Day day = trackerRepository.findById(dayId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkPermission(day, SecurityUtil.getCurrentUserId());

        String preImagePath = day.getImage();
        String path = FileUtil.uploadFile(image, "day", 0);
        day.setImage(path);
        trackerRepository.save(day);
        FileUtil.deleteFile(preImagePath);

        if(path.equals(""))
            return "";
        return GlobalValue.getImageServerURI() + path;
    }
}
