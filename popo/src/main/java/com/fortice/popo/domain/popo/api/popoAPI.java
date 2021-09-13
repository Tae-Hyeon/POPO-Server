package com.fortice.popo.domain.popo.api;

import com.fortice.popo.domain.popo.application.PopoCrudService;
import com.fortice.popo.domain.popo.dto.PopoCreateRequest;
import com.fortice.popo.global.common.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@RestController
@RequestMapping(value = "/popo", produces = {MediaType.APPLICATION_JSON_VALUE})
public class popoAPI {
    @Autowired
    PopoCrudService popoCrudService;
    /**
     * 포포 리스트 조회 API
     * @return 특정 계정의 포포 리스트
     */

    @Operation(summary = "포포 리스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Response getPopoList() throws Exception {
        return popoCrudService.getPopoList();
    }

    /**
     * 포포 정보 조회 API
     * @param popoId
     * @return popoId의 ID 값을 가지는 포포 정보
     * @throws Exception
     */
    @Operation(summary = "특정 포포 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @RequestMapping(value = "/{popoId}", method= RequestMethod.GET)
    public @ResponseBody Response getPopo (
            @Parameter(description = "포포 ID", required = true, example = "1")
            @Valid @Min(value = 1, message = "요청 url의 최소값은 1입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable("popoId") Integer popoId) throws Exception {
        return popoCrudService.getPopo(popoId);
    }

    @RequestMapping(method = RequestMethod.POST)
    // @ApiOperation(value = "포포 추가")
    public @ResponseBody Response insertPopo(
            @Valid @RequestBody PopoCreateRequest request) throws Exception{
        return popoCrudService.insertPopo(request);
    }

    @RequestMapping(path = "/{popoId}", method = RequestMethod.DELETE)
    // @ApiOperation(value = "포포 삭제")
    public @ResponseBody Response deletePopo(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable Integer popoId) throws Exception {
        return popoCrudService.deletePopo(popoId);
    }

    @RequestMapping(value = "/popo/{popoId}/background", method = RequestMethod.PATCH)
    // @ApiOperation(value = "포포 배경 수정")
    public @ResponseBody Response changeBackground(
            @Valid @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
            @Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
            @PathVariable("popoId") Integer popoId,
            @RequestBody String background) throws Exception {
        return popoCrudService.changeBackground(popoId, background);
    }
}
