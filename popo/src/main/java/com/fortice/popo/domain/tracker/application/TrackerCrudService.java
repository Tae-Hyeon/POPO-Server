package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.domain.tracker.dto.TrackerResponse;
import com.fortice.popo.global.common.response.Response;
import com.fortice.popo.global.util.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrackerCrudService {
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
}
