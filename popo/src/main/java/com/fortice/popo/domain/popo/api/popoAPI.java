package com.fortice.popo.domain.popo.api;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.application.PopoCrudService;
import com.fortice.popo.domain.popo.dao.OptionDAO;
import com.fortice.popo.domain.popo.dao.PopoDAO;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.aop.annotation.LogTime;
import com.fortice.popo.global.common.response.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/popo", produces = {MediaType.APPLICATION_JSON_VALUE})
public class popoAPI {
    @Autowired
    PopoCrudService popoCrudService;
    /**
     * 포포 리스트 조회 API
     * @return 특정 계정의 포포 리스트
     */
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Object getPopoList() {
        Response<List<Popo>> response = popoCrudService.getPopoList();
        return response;
    }

    /**
     * 포포 정보 조회 API
     * @param popoId
     * @return popoId의 ID 값을 가지는 포포 정보
     * @throws Exception
     */
    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
    public @ResponseBody Response getPopo(@PathVariable("popoId") Long popoId) throws Exception {
        Response<Popo> response = popoCrudService.getPopo(popoId);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response insertPopo(@RequestBody PopoCreateRequest request) {
        Response<Object> response = popoCrudService.insertPopo(request);
        return response;
    }

//    @RequestMapping(value = "/popo/{popoId}/{changer}", method = RequestMethod.PATCH)
//    public @ResponseBody Response changeBackground(@PathVariable("popoId") Long popoId, @PathVariable("changer") String changer) {
//        return json;
//    }
}
