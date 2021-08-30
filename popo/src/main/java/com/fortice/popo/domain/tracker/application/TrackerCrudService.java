package com.fortice.popo.domain.tracker.application;

import com.fortice.popo.domain.tracker.dao.TrackerOptionDAO;
import com.fortice.popo.domain.tracker.dao.TrackerDAO;
import com.fortice.popo.domain.tracker.dto.DayResponse;
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

    public Response getOneDay(Integer popoId, Integer dayId){
        //List<OptionDTO> options = optionDAO.findOptionsByPopoId(popoId);
        //List<DayResponse> day = trackerDAO.getDayResponseById(dayId);
        //DayResponse day = trackerDAO.getDayTestById(dayId);

        Response response = new Response(200, "조회 성공", null);
        return response;
    }
}
