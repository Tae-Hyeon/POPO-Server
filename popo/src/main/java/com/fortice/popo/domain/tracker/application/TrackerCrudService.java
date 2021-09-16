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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrackerCrudService {
    @Autowired
    PopoDAO popoDAO;
    @Autowired
    OptionDAO optionDAO;
    @Autowired
    TrackerDAO trackerDAO;
    @Autowired
    TrackerContentDAO trackerContentDAO;

    Checker checker = new Checker();
    Formatter formatter = new Formatter();

    @Value("${path.local.root}")
    String rootPath;

    public Response getTracker(Integer popoId, String year, String month) throws Exception {

        Popo popo = popoDAO.findById(popoId)
                .orElseThrow(NotFoundDataException::new);
        checker.checkPermission(popo, 1);

        String dateFormat = formatter.getDateFormatByYearAndMonth(year, month);
        List<DayDTO> tracker = trackerDAO.getDayDTOById(popoId, dateFormat);

        TrackerResponse trackerResponse = TrackerResponse.builder()
                .category(popo.getCategory())
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

    public Response insertOneDay(Integer popoId, CreateDayRequest request) throws Exception{
        Date date = new SimpleDateFormat("YYYY-mm-dd").parse(request.getDate());
        Day day = trackerDAO.save(Day.builder()
                .image("")
                .popo(Popo.builder().id(popoId).build())
                .date(date)
                .build()
        );

        List<Option> options = optionDAO.getIdsByPopo(popoId);
        for(Option option : options) {
            OptionContent newContents = OptionContent.builder()
                    .option(option)
                    .day(day)
                    .contents("")
                    .build();

            newContents = trackerContentDAO.save(newContents);
        }

        DayResponse dayResponse = new DayResponse(day,trackerContentDAO.findOptionsByDayId(day.getId()));

        Response response = new Response(200, "조회 성공", dayResponse);
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
        checker.checkFileType(image);

        Day day = trackerDAO.findById(dayId)
                .orElseThrow(NotFoundDataException::new);

        checker.checkPermission(day, 1);

        File deleteFile = new File(rootPath + day.getImage());

        String path = formatter.getPathWithResourceAndFile("tracker", new Date(), 0, image.getOriginalFilename());
        File dest = new File(rootPath + path);
        image.transferTo(dest);

        day.setImage(path);
        trackerDAO.save(day);
        if(deleteFile.exists()) {
            if(deleteFile.delete())
                System.out.println(deleteFile.getPath() + "사진 삭제 성공");
            else{
                //TODO ERROR LOG
                System.out.println(deleteFile.getPath() + "사진 삭제 실패");
            }
        }

        Response response = new Response(200, "수정 성공", path);
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
}
