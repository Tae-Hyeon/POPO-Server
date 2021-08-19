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

    private Response returnResponse(int code, String message, Object data) {
        Response<Object> response = new Response<>(code, message, data);
        return response;
    }

    public Response getPopoList() {
        List<Popo> popoList = popoDAO.findAll();

        return returnResponse(200, "포포 리스트 조회 성공", popoList);
    }

    public Response getPopo(Integer popoId) throws Exception{
        Optional<Popo> popo = popoDAO.findById(popoId);
        if(popoId > 3)
            throw new Exception("popo not found");

        return returnResponse(200, "포포 조회 성공", popo.get());
    }

    public Response insertPopo(PopoCreateRequest request) {
        Popo newPopo = request.getPopo();
        newPopo = popoDAO.save(newPopo);
        if(!request.isOptionEmpty()) {
            List<Option> newOptions = request.getOptions(newPopo.getId());
            OptionDAO.saveAll(newOptions);
        }

        return returnResponse(200, "포포 생성 성공", newPopo);
    }

    public Response deletePopo(Integer popoId) {
        popoDAO.deleteById(popoId);

        return returnResponse(200, "포포 삭제 성공", null);
    }

    public Response changeBackground(Integer popoId, String background){
        Optional<Popo> popo = popoDAO.findById(popoId);
        popo.get().setBackground(background);
        popoDAO.save(popo.get());

        return returnResponse(200, "포포 배경 수정 성공", null);
    }
}
