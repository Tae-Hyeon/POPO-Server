package com.fortice.popo.domain.tracker.api;

import com.fortice.popo.domain.tracker.application.TrackerCrudService;
import com.fortice.popo.domain.tracker.dto.CreateDayRequest;
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

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Response getTracker(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @RequestParam String year,
            @RequestParam String month) throws Exception {
        return trackerCrudService.getTracker(popoId, year, month);
    }

    @RequestMapping(path = "/{dayId}", method = RequestMethod.GET)
    public @ResponseBody Response getOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId) throws Exception {
        return trackerCrudService.getOneDay(popoId, dayId);
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response insertOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @RequestBody CreateDayRequest request) throws Exception {
        return trackerCrudService.insertOneDay(popoId, request);
    }

    @RequestMapping(path = "/{dayId}/contents", method = RequestMethod.PATCH)
    public @ResponseBody Response patchContents() throws Exception {
        return trackerCrudService.patchContents();
    }
}
