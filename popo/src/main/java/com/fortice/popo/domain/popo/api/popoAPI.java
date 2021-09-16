package com.fortice.popo.domain.popo.api;

import com.fortice.popo.domain.popo.application.PopoCrudService;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.common.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Api("POPO API")
@RestController
@RequestMapping(value = "/popo", produces = {MediaType.APPLICATION_JSON_VALUE})
public class popoAPI {
    @Autowired
    PopoCrudService popoCrudService;
    /**
     * 포포 리스트 조회 API
     * @return 특정 계정의 포포 리스트
     */
    @ApiOperation("포포 리스트 조회")
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Response getPopoList() throws Exception {
        return popoCrudService.getPopoList();
    }

//    /**
//     * 포포 정보 조회 API
//     * @param popoId
//     * @return popoId의 ID 값을 가지는 포포 정보
//     * @throws Exception
//     */
//    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
//    public @ResponseBody Response getPopo (
//            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
//            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
//            @PathVariable("popoId") Integer popoId) throws Exception {
//        return popoCrudService.getPopo(popoId);
//    }

    @RequestMapping(method = RequestMethod.POST)
    // @ApiOperation(value = "포포 추가")
    public @ResponseBody Response setDefaultPopo(
            @RequestPart List<MultipartFile> backgrounds) throws Exception{
        return popoCrudService.setDefaultPopo(backgrounds);
    }

    @RequestMapping(path = "/{popoId}", method = RequestMethod.POST)
    // @ApiOperation(value = "포포 추가")
    public @ResponseBody Response insertPopo(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId,
            @Valid @RequestBody PopoCreateRequest request) throws Exception{
        return popoCrudService.insertPopo(popoId, request);
    }

    @RequestMapping(path = "/{popoId}", method = RequestMethod.DELETE)
    // @ApiOperation(value = "포포 삭제")
    public @ResponseBody Response deletePopo(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId) throws Exception {
        return popoCrudService.deletePopo(popoId);
    }

    @RequestMapping(value = "/popo/{popoId}/background", method = RequestMethod.PATCH)
    // @ApiOperation(value = "포포 배경 수정")
    public @ResponseBody Response changeBackground(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+", message = "숫자만 입력 가능합니다")
            @PathVariable("popoId") Integer popoId,
            @RequestBody String background) throws Exception {
        return popoCrudService.changeBackground(popoId, background);
    }

}
