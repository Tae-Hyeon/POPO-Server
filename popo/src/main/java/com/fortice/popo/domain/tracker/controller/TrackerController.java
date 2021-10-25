package com.fortice.popo.domain.tracker.controller;

import com.fortice.popo.domain.tracker.service.TrackerService;
import com.fortice.popo.domain.tracker.dto.CreateDayRequest;
import com.fortice.popo.global.common.response.Body;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/popo/{popoId}/tracker", produces = {MediaType.APPLICATION_JSON_VALUE})
public class TrackerController {
    private final TrackerService trackerService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Body> getTracker(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid
            @Pattern(regexp = "199[0-9]|20[0-3][0-9]", message = "yyyy 형식만 입력 가능합니다")
            @RequestParam String year,
            @Valid
            @Pattern(regexp = "1[0-2]|[0][1-9]|[1-9]", message = "mm 형식만 입력 가능합니다")
            @RequestParam String month) throws Exception {
        return ResponseEntity.ok(
                makeBody(200, "트래커 조회 성공", trackerService.getTracker(popoId, year, month))
        );
    }

    @RequestMapping(path = "/{dayId}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Body> getOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId) throws Exception {
        return ResponseEntity.ok(
                makeBody(200, "데이터 조회 성공", trackerService.getOneDay(popoId, dayId))
        );
    }

    //TODO: insert 시 생성된 id값만 response로 보내주고, 조회는 다시 API를 호출하도록 만들어야함
    @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public @ResponseBody
    ResponseEntity<Body> insertOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @ModelAttribute("request") CreateDayRequest request) throws Exception {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(
                makeBody(200, "추가 완료", trackerService.insertOneDay(popoId, request)),
                header,
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{dayId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<Body> deleteOneDay(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId) throws Exception {
        return ResponseEntity.ok(
                makeBody(200, "삭제 완료", trackerService.deleteOneDay(dayId))
        );
    }

    @RequestMapping(path = "/{dayId}/contents/{contentId}", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<Body> patchContents(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer contentId,
            @Valid @RequestBody String contents) throws Exception {

        return ResponseEntity.ok(
                makeBody(200, "내용 수정 완료", trackerService.patchContents(contentId, contents))
        );
    }

    @RequestMapping(path = "/{dayId}/image", method = RequestMethod.PATCH, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    ResponseEntity<Body> patchImage(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer dayId,
            @RequestPart MultipartFile image) throws Exception {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(
                makeBody(200, "이미지 수정 완료", trackerService.patchImage(dayId, image)),
                header,
                HttpStatus.OK
        );
    }

    private Body<Object> makeBody(int code, String message, Object data) throws Exception{
        return new Body<Object>(code, message, data);
    }
}
