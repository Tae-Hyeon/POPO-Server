package com.fortice.popo.domain.tracker.api;

import com.fortice.popo.domain.tracker.application.TrackerCrudService;
import com.fortice.popo.domain.tracker.dto.CreateDayRequest;
import com.fortice.popo.global.common.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid
            @Pattern(regexp = "199[0-9]|20[0-3][0-9]", message = "yyyy 형식만 입력 가능합니다")
            @RequestParam String year,
            @Valid
            @Pattern(regexp = "1[0-2]|[0][1-9]|[1-9]", message = "mm 형식만 입력 가능합니다")
            @RequestParam String month) throws Exception {
        return trackerCrudService.getTracker(popoId, year, month);
    }

    @RequestMapping(path = "/{dayId}", method = RequestMethod.GET)
    public @ResponseBody Response getOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId) throws Exception {
        return trackerCrudService.getOneDay(popoId, dayId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody Response insertOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @RequestPart MultipartFile image,
            @Valid @RequestBody CreateDayRequest request) throws Exception {
        return trackerCrudService.insertOneDay(popoId, image, request);
    }

    @RequestMapping(path = "/{dayId}/contents/{contentId}", method = RequestMethod.PATCH)
    public @ResponseBody Response patchContents(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer contentId,
            @Valid @RequestBody String contents) throws Exception {
        return trackerCrudService.patchContents(contentId, contents);
    }

    @RequestMapping(path = "/{dayId}/image", method = RequestMethod.PATCH, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody Response patchImage(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId,
            @RequestPart MultipartFile image) throws Exception {
        return trackerCrudService.patchImage(dayId, image);
    }
}
