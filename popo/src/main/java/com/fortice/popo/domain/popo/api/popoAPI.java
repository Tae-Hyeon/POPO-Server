package com.fortice.popo.domain.popo.api;

import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dao.PopoDAO;
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

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Object getPopoList() {
        List<Popo> popoList = popoDAO.findAll();

        return popoList;
    }

    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
    public @ResponseBody Object getPopo(@PathVariable("popoId") Long popoId) {
        Optional<Popo> popo = popoDAO.findById(popoId);

        return popo;
    }

    @RequestMapping(value = "/popo", method = RequestMethod.POST)
    public @ResponseBody Object insertPopo(@RequestBody Popo newPopo) {
        popoDAO.save(newPopo);
        return newPopo;
    }

    @RequestMapping(value = "/popo/{popoId}", method = RequestMethod.PATCH)
    public @ResponseBody Object changeBackground(@ResponseBody)
}
