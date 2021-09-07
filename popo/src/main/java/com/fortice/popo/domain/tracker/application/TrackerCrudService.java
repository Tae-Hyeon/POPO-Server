package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.OptionContent;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.CreateDayRequest;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.domain.tracker.dto.TrackerResponse;
import com.fortice.popo.global.common.response.Response;
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
        if(day.isPresent())
        {
            Response response = new Response(400, "이미 생성한 날짜입니다.", null);
            return response;
        }

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

    public Response patchContents() throws Exception{

        Response response = new Response(200, "조회 성공", null);
        return response;
    }
}
