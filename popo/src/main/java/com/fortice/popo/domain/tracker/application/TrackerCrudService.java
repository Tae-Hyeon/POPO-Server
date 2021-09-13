package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.model.*;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.CreateDayRequest;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.domain.tracker.dto.TrackerResponse;
import com.fortice.popo.global.common.response.Response;
import com.fortice.popo.global.error.exception.NoPermissionException;
import com.fortice.popo.global.error.exception.NotFoundDataException;
import com.fortice.popo.global.util.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrackerCrudService {
    @Autowired
    OptionDAO optionDAO;
    @Autowired
    TrackerDAO trackerDAO;
    @Autowired
    TrackerContentDAO trackerContentDAO;

    Checker checker;

    public Response getTracker(Integer popoId, String year, String month) throws Exception {
        LocalDate now = LocalDate.now();

        year = year.isBlank() ? checker.checkDateForm(now.getYear()) : year;
        month = month.isBlank() ? checker.checkDateForm(now.getMonthValue()) : month;

        String dateFormat = year + "-" + month;
        List<TrackerResponse> tracker = trackerDAO.getTrackerResponseById(popoId, dateFormat);

        Response response = new Response(200, "조회 성공", tracker);
        return response;
    }

    public Response getOneDay(Integer popoId, Integer dayId) throws Exception{
        List<OptionContentDTO> options = trackerContentDAO.findOptionsByDayId(dayId);
        DayResponse day = trackerDAO.getDayResponseById(dayId);
        day.setOptions(options);

        Response response = new Response(200, "조회 성공", day);
        return response;
    }

    public Response insertOneDay(Integer popoId, CreateDayRequest request) throws Exception{
        Date date = new SimpleDateFormat("YYYY-mm-dd").parse(request.getDate());
        Optional<Day> day = trackerDAO.findByDate(date);

        checkPermissionDay(day, 1);

        Day newDay = Day.builder()
                .popo(Popo.builder().id(popoId).build())
                .date(date)
                .build();

        newDay = trackerDAO.save(newDay);

        List<Option> options = optionDAO.getIdsByPopo(popoId);

        for(Option option : options) {
            OptionContent newContents = OptionContent.builder()
                    .option(option)
                    .day(newDay)
                    .contents("")
                    .build();

            newContents = trackerContentDAO.save(newContents);
        }
        Response response = new Response(200, "조회 성공", null);
        return response;
    }

    public Response patchContents(Integer contentId, String contents) throws Exception{
        Optional<OptionContent> content = trackerContentDAO.findById(contentId);

        checkPermissionContent(content, 1);

        content.get().setContents(contents);
        trackerContentDAO.save(content.get());

        Response response = new Response(200, "수정 성공", null);
        return response;
    }

    private void checkPermissionContent(Optional<OptionContent> content, int userId) throws Exception{
        content.orElseThrow(NotFoundDataException::new);
        if(!checker.checkOwner(content.get().getOwnerId(), userId))
            throw new NoPermissionException();
    }

    private void checkPermissionDay(Optional<Day> day, int userId) throws Exception{
        day.orElseThrow(NotFoundDataException::new);
        if(!checker.checkOwner(day.get().getOwnerId(), userId))
            throw new NoPermissionException();
    }
}
