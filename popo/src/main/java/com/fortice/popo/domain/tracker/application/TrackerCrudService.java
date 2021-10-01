package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.model.*;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.*;
import com.fortice.popo.global.common.response.Response;
import com.fortice.popo.global.error.exception.NoPermissionException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import com.fortice.popo.global.util.FileUtil;
import com.fortice.popo.global.util.Formatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    private Checker checker = new Checker();
    private Formatter formatter = new Formatter();
    private FileUtil fileUtil = new FileUtil();

    @Value("${path.root}")
    private String rootPath;
    @Value("${uri.image-server}")
    private String imageServerURI;

    public Response getTracker(Integer popoId, String year, String month) throws Exception {

        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        checker.checkPermission(popo, 1);

        String dateFormat = formatter.getDateFormatByYearAndMonth(year, month);
        List<DayDTO> tracker = trackerDAO.getDayDTOById(popoId, dateFormat);

        TrackerResponse trackerResponse = TrackerResponse.builder()
                .category(popo.getCategory())
                .background(popo.getTracker_image())
                .build();
        trackerResponse.updateTracker(year, month, tracker);

        Response response = new Response(200, "조회 성공", trackerResponse);
        return response;
    }

    public Response getOneDay(Integer popoId, Integer dayId) throws Exception{
        //TODO 유저 확인 과정 필요
        List<OptionContentDTO> options = trackerContentDAO.findOptionsByDayId(dayId);
        checker.checkEmpty(options);

        DayResponse day = trackerDAO.getDayResponseById(dayId);

        day.setOptions(options);

        Response response = new Response(200, "조회 성공", day);
        return response;
    }

    public Response insertOneDay(Integer popoId, MultipartFile image, CreateDayRequest request) throws Exception{
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


        Response response = new Response(200, "생성 성공", new DayResponse(newDay, contents));
        return response;
    }

    public Response patchContents(Integer contentId, String contents) throws Exception{
        OptionContent content = trackerContentDAO.findById(contentId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkEmpty(content);
        checker.checkPermission(content, 1);

        content.setContents(contents);
        trackerContentDAO.save(content);

        Response response = new Response(200, "수정 성공", null);
        return response;
    }

    public Response patchImage(Integer dayId, MultipartFile image) throws Exception{
        Day day = trackerDAO.findById(dayId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(day, 1);

        String preImagePath = day.getImage();
        String path = fileUtil.uploadFile(image, "day", 0);
        day.setImage(path);
        trackerDAO.save(day);
        fileUtil.deleteFile(preImagePath);

        Response<String> response = new Response<String>(200, "수정 성공", imageServerURI + path);
        return response;
    }

    private void createNewDay(Integer popoId, String date) throws Exception{
        Day newDay = Day.builder()
                .popo(Popo.builder().id(popoId).build())
                .date(new Date(date))
                .image("")
                .build();

        newDay = trackerDAO.save(newDay);
    }

    private void setPathWithImageServerURI(List<Day> dayList) {
        for(Day day : dayList)
            day.setImage(this.imageServerURI + day.getImage());
    }
}
