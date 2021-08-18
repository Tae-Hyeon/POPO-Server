package com.fortice.popo.domain.popo.application;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.common.response.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PopoCrudService {
    @Autowired
    PopoDAO popoDAO;
    @Autowired
    OptionDAO OptionDAO;

    public Response getPopoList() {
        List<Popo> popoList = popoDAO.findAll();
        Response<List<Popo>> response = new Response<>(200, "포포 리스트 조회 성공", popoList);

        return response;
    }

    public Response getPopo(Long popoId) throws Exception{
        Optional<Popo> popo = popoDAO.findById(popoId);
        if(popoId > 3)
            throw new Exception("popo not found");
        Response<Popo> response = new Response<Popo>(200, "포포 조회 성공", popo.get());

        return response;
    }

    public Response insertPopo(PopoCreateRequest request) {
        Popo newPopo = request.getPopo();
        newPopo = popoDAO.save(newPopo);
        List<Option> newOptions = request.getOptions(newPopo.getId());
        OptionDAO.saveAll(newOptions);

        Response<Object> response = new Response<>(200, "포포 생성 성공", newPopo);
        return response;
    }
}
