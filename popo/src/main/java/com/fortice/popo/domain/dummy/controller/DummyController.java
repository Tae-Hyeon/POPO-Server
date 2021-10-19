package com.fortice.popo.domain.dummy.controller;

import com.fortice.popo.domain.dummy.dto.DummyRequest;
import com.fortice.popo.domain.dummy.service.DummyService;
import com.fortice.popo.global.common.response.Body;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("POPO Dummy Data Import API (only developer)")
@RestController
@RequestMapping(value = "/dummy", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DummyController {

    DummyService dummyService;

    @Autowired
    public void setDummyService(DummyService dummyService) {
        this.dummyService = dummyService;
    }

//    @RequestMapping(value = "/option/{category}", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public @ResponseBody ResponseEntity<Body> setDummyFromOption(
//            @PathVariable String category,
//            @ModelAttribute DummyRequest dummyRequest
//            ) throws Exception{
//        return ResponseEntity.ok(makeBody(200, "success", dummyService.setDummyFromOption(category, dummyRequest)));
//    }

    @RequestMapping(value = "/day/{category}", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<Body> setDummyFromDay(
            @PathVariable String category,
            @ModelAttribute DummyRequest dummyRequest
    ) throws Exception{
        dummyService.setDummyFromDay(category, dummyRequest);
        return ResponseEntity.ok(makeBody(200, "success", "test"));
    }

    private Body<Object> makeBody(int code, String message, Object data) throws Exception{
        return new Body<Object>(code, message, data);
    }
}
