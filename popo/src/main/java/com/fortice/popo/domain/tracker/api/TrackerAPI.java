package com.fortice.popo.domain.tracker.api;

import com.fortice.popo.domain.tracker.application.TrackerCrudService;
import com.fortice.popo.global.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/popo/{popoId}/tracker", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TrackerAPI {
    @Autowired
    TrackerCrudService trackerCrudService;

    @RequestMapping(path = "/{dayId}", method = RequestMethod.GET)
    public @ResponseBody
    Response deletePopo(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId) throws Exception {
        return trackerCrudService.getOneDay(popoId, dayId);
    }
}
