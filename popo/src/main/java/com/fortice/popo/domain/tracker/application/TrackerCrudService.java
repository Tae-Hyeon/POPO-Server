package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.tracker.dao.TrackerContentDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.global.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrackerCrudService {
    @Autowired
    TrackerDAO trackerDAO;
    @Autowired
    TrackerContentDAO trackerContentDAO;

    public Response getOneDay(Integer popoId, Integer dayId){
        List<OptionContentDTO> options = trackerContentDAO.findOptionsByDayId(dayId);
        DayResponse day = trackerDAO.getDayResponseById(dayId);
        day.setOptions(options);

        Response response = new Response(200, "조회 성공", day);
        return response;
    }
}
