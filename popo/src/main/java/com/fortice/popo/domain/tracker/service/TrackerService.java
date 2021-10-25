package com.fortice.popo.domain.tracker.service;

import com.fortice.popo.domain.model.*;
import com.fortice.popo.domain.popo.repository.OptionRepository;
import com.fortice.popo.domain.popo.repository.PopoRepository;
import com.fortice.popo.domain.tracker.repository.TrackerContentRepository;
import com.fortice.popo.domain.tracker.repository.TrackerRepository;
import com.fortice.popo.domain.tracker.dto.*;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import com.fortice.popo.global.util.Formatter;
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
@RequiredArgsConstructor
@Transactional
public class TrackerService {
    private final PopoRepository popoRepository;
    private final OptionRepository optionRepository;
    private final TrackerRepository trackerRepository;
    private final TrackerContentRepository trackerContentRepository;

    @Value("${uri.image-server}")
    private String imageServerURI;
    @Value("${path.root}")
    private String rootPath;

    public TrackerResponse getTracker(Integer popoId, String year, String month) throws Exception {

        Popo popo = popoRepository.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        Checker.checkPermission(popo, 1);

        String dateFormat = Formatter.getDateFormatByYearAndMonth(year, month);
        List<DayDTO> tracker = trackerRepository.getDayDTOById(popoId, dateFormat);
        for (DayDTO day : tracker)
            day.setUri(imageServerURI);

        TrackerResponse trackerResponse = TrackerResponse.builder()
                .category(popo.getCategory())
                .background(imageServerURI + popo.getTracker_image())
                .build();
        trackerResponse.updateTracker(year, month, tracker);

        return trackerResponse;
    }

    public DayResponse getOneDay(Integer popoId, Integer dayId) throws Exception {
        //TODO 유저 확인 과정 필요
        List<OptionContentDTO> options = trackerContentRepository.findOptionsByDayId(dayId);
        Checker.checkEmpty(options);

        DayResponse dayResponse = trackerRepository.getDayResponseById(dayId);

        dayResponse.setUri(imageServerURI);
        dayResponse.setOptions(options);

        return dayResponse;
    }

    public DayResponse insertOneDay(Integer popoId, CreateDayRequest request) throws Exception {
        FileUtil fileUtil = new FileUtil(rootPath);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate());
        Day newDay = Day.builder()
                .popo(Popo.builder().id(popoId).build())
                .date(date)
                .image(fileUtil.uploadFile(request.getImage(), "tracker", 0))
                .build();

        newDay = trackerRepository.save(newDay);

        List<Option> options = optionRepository.getOptionByPopo(popoId);
        List<OptionContent> newContents = new ArrayList<>();
        List<OptionContentDTO> contents = new ArrayList<>();
        for (Option option : options) {
            OptionContent newContent = OptionContent.builder()
                    .option(option)
                    .day(newDay)
                    .contents(request.getContentsByOptionId(option.getId()))
                    .build();

            newContents.add(newContent);
            contents.add(new OptionContentDTO(option, newContent));
        }

        trackerContentRepository.saveAll(newContents);
        DayResponse dayResponse = new DayResponse(newDay, contents);
        dayResponse.setUri(imageServerURI);

        return dayResponse;
    }

    public String patchContents(Integer contentId, String contents) throws Exception {
        OptionContent content = trackerContentRepository.findById(contentId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkEmpty(content);
        Checker.checkPermission(content, 1);

        content.setContents(contents);
        trackerContentRepository.save(content);

        return "";
    }

    public String patchImage(Integer dayId, MultipartFile image) throws Exception {
        FileUtil fileUtil = new FileUtil(rootPath);

        Day day = trackerRepository.findById(dayId)
                .orElseThrow(NotFoundDataException::new);

        Checker.checkPermission(day, 1);

        String preImagePath = day.getImage();
        String path = fileUtil.uploadFile(image, "day", 0);
        day.setImage(path);
        trackerRepository.save(day);
        fileUtil.deleteFile(preImagePath);

        return imageServerURI + path;
    }
}
