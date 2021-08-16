package com.fortice.popo.domain.popo.api;

import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dao.PopoDAO;
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
    PopoDAO popoDAO;

    @LogTime
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Object getPopoList() {
        List<Popo> popoList = popoDAO.findAll();

        return popoList;
    }

    @LogTime
    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
    public @ResponseBody Object getPopo(@PathVariable("popoId") Long popoId) throws Exception {
        Optional<Popo> popo = popoDAO.findById(popoId);
        if(popoId > 3)
            throw new Exception("popo found");
        Response<Popo> response = new Response<Popo>(200, "포포 조회 성공", popo.get());
        return response;
    }

    @RequestMapping(value = "/popo", method = RequestMethod.POST)
    public @ResponseBody Object insertPopo(@RequestBody Popo newPopo) {
        popoDAO.save(newPopo);
        return newPopo;
    }

    @RequestMapping(value = "/popo/{popoId}/{changer}", method = RequestMethod.PATCH)
    public @ResponseBody Object changeBackground(@PathVariable("popoId") Long popoId, @PathVariable("changer") String changer) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("message", changer + " 수정");
        return json;
    }
}
