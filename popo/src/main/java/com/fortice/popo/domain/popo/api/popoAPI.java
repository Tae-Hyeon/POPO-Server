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
        return popoCrudService.getPopoList();
    }

    /**
     * 포포 정보 조회 API
     * @param popoId
     * @return popoId의 ID 값을 가지는 포포 정보
     * @throws Exception
     */
    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
    public @ResponseBody Response getPopo(@PathVariable("popoId") Integer popoId) throws Exception {
        return popoCrudService.getPopo(popoId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response insertPopo(@Valid @RequestBody PopoCreateRequest request) {
        return popoCrudService.insertPopo(request);
    }

    @RequestMapping(path = "/{popoId}", method = RequestMethod.DELETE)
    public @ResponseBody Response deletePopo(@PathVariable Integer popoId) {
        return popoCrudService.deletePopo(popoId);
    }

    @RequestMapping(value = "/popo/{popoId}/background", method = RequestMethod.PATCH)
    public @ResponseBody Response changeBackground(@PathVariable("popoId") Integer popoId, @RequestBody String background) {
        return popoCrudService.changeBackground(popoId, background);
    }
}
