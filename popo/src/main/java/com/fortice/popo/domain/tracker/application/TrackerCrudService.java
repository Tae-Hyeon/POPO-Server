package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.model.*;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.*;
import com.fortice.popo.global.common.response.Body;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import com.fortice.popo.global.util.Formatter;
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
public class TrackerCrudService {
    @Autowired
    private PopoDAO popoDAO;
    @Autowired
    private OptionDAO optionDAO;
    @Autowired
    private TrackerDAO trackerDAO;
    @Autowired
    private TrackerContentDAO trackerContentDAO;

    @Value("${uri.image-server}")
    private String imageServerURI;
    @Value("${path.root}")
    private String rootPath;

    private static final Checker checker = new Checker();
    private static final Formatter formatter = new Formatter();

    public Body getTracker(Integer popoId, String year, String month) throws Exception {

        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        checker.checkPermission(popo, 1);

        String dateFormat = formatter.getDateFormatByYearAndMonth(year, month);
        List<DayDTO> tracker = trackerDAO.getDayDTOById(popoId, dateFormat);
        for(DayDTO day : tracker)
            day.setUri(imageServerURI);

        TrackerResponse trackerResponse = TrackerResponse.builder()
                .category(popo.getCategory())
                .background(imageServerURI + popo.getTracker_image())
                .build();
        trackerResponse.updateTracker(year, month, tracker);

        Body body = new Body(200, "조회 성공", trackerResponse);
        return body;
    }

    public Body getOneDay(Integer popoId, Integer dayId) throws Exception{
        //TODO 유저 확인 과정 필요
        List<OptionContentDTO> options = trackerContentDAO.findOptionsByDayId(dayId);
        checker.checkEmpty(options);

        DayResponse dayResponse = trackerDAO.getDayResponseById(dayId);

        dayResponse.setUri(imageServerURI);
        dayResponse.setOptions(options);

        Body body = new Body(200, "조회 성공", dayResponse);
        return body;
    }

    public Body insertOneDay(Integer popoId, MultipartFile image, CreateDayRequest request) throws Exception{
        FileUtil fileUtil = new FileUtil(rootPath);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDate());
        Day newDay = Day.builder()
                .popo(Popo.builder().id(popoId).build())
                .date(date)
                .image(fileUtil.uploadFile(image, "tracker", 0))
                .build();

        newDay = trackerDAO.save(newDay);

        List<Option> options = optionDAO.getIdsByPopo(popoId);
        List<OptionContentDTO> contents = new ArrayList<>();
        for(Option option : options) {
            OptionContent newContents = OptionContent.builder()
                    .option(option)
                    .day(newDay)
                    .contents("option")
                    .build();

            contents.add(new OptionContentDTO(option, newContents));

            newContents = trackerContentDAO.save(newContents);
        }

        DayResponse dayResponse = new DayResponse(newDay, contents);
        dayResponse.setUri(imageServerURI);

        Body body = new Body(200, "생성 성공", dayResponse);
        return body;
    }

    public Body patchContents(Integer contentId, String contents) throws Exception{
        OptionContent content = trackerContentDAO.findById(contentId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkEmpty(content);
        checker.checkPermission(content, 1);

        content.setContents(contents);
        trackerContentDAO.save(content);

        Body body = new Body(200, "수정 성공", null);
        return body;
    }

    public Body patchImage(Integer dayId, MultipartFile image) throws Exception{
        FileUtil fileUtil = new FileUtil(rootPath);

        Day day = trackerDAO.findById(dayId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(day, 1);

        String preImagePath = day.getImage();
        String path = fileUtil.uploadFile(image, "day", 0);
        day.setImage(path);
        trackerDAO.save(day);
        fileUtil.deleteFile(preImagePath);

        Body<String> body = new Body<String>(200, "수정 성공", imageServerURI + path);
        return body;
    }
}
