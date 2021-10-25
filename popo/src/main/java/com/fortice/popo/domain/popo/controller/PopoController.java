package com.fortice.popo.domain.popo.controller;

import com.fortice.popo.domain.popo.service.PopoService;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.common.response.Body;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

@Api("POPO API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/popo", produces = {MediaType.APPLICATION_JSON_VALUE})
public class PopoController {
    private final PopoService popoService;

    /**
     * 포포 리스트 조회 API
     * @return 특정 계정의 포포 리스트
     */
    @ApiOperation(value = "포포 리스트 조회",
            notes = "토큰으로 사용자를 판별해 포포 리스트를 반환")
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody ResponseEntity<Body> getPopoList() throws Exception {
        return ResponseEntity.ok(
                makeBody(200, "포포 리스트 조회 성공", popoService.getPopoList())
        );
    }

    @ApiOperation(value = "온보딩 후 첫 컨셉 이미지 세팅")
    @ApiImplicitParam(name = "backgrounds", value = "포포 컨셉 이미지 12개 파일")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<Body> setDefaultPopo(
            @RequestPart List<MultipartFile> backgrounds) throws Exception{
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(
                makeBody(200, "포포 컨셉 세팅 완료", popoService.setDefaultPopo(backgrounds)),
                header,
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{popoId}", method = RequestMethod.POST)
    // @ApiOperation(value = "포포 설정")
    public @ResponseBody ResponseEntity<Body> insertPopo(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @RequestBody PopoCreateRequest request) throws Exception{

        return ResponseEntity.ok(
                makeBody(200, "포포 설정 성공", popoService.insertPopo(popoId, request))
        );
    }

    @RequestMapping(path = "/{popoId}/option", method = RequestMethod.GET)
    @ApiOperation(value = "포포 옵션 목록(날짜 추가 시 옵션 보기)")
    public @ResponseBody ResponseEntity<Body> getOptions(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId) throws Exception{
        return ResponseEntity.ok(
                makeBody(200, "포포 설정 성공", popoService.getOptions(popoId))
        );
    }
//    @RequestMapping(path = "/{popoId}", method = RequestMethod.DELETE)
//    // @ApiOperation(value = "포포 삭제")
//    public @ResponseBody Response deletePopo(
//            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
//            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
//            @PathVariable Integer popoId) throws Exception {
//        return ResponseEntity.ok(
//                makeBody(200, "포포 삭제 성공", popoCrudService.deletePopo(popoId))
//        );
//    }

    @ApiOperation(value = "트래커 배경 수정")
    @RequestMapping(value = "/{popoId}/background", method = RequestMethod.PATCH,
            produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody ResponseEntity<Body> changeBackground(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[0-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable("popoId") Integer popoId,
            @RequestPart MultipartFile background) throws Exception {

        String url = popoService.changeBackground(popoId, background);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity(
                makeBody(200, "트래커 배경 수정 성공", url),
                header,
                HttpStatus.OK
        );
    }


    private Body<Object> makeBody(int code, String message, Object data) throws Exception{
        return new Body<Object>(code, message, data);
    }
}
