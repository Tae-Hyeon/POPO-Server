package com.fortice.popo.domain.popo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionDTO {
    @ApiModelProperty(example = "제목")
    @ApiParam(value = "옵션 이름")
    @NotBlank(message = "옵션의 이름을 설정해주세요")
    @Length(min=0, max=30)
    private String name;

    @ApiModelProperty(example = "0")
    @ApiParam(value = "옵션 타입(String: 0, Integer: 1)")
    @NotNull(message = "옵션의 타입을 설정해주세요(String: 0, INT: 1")
    @Min(0)
    @Max(1)
    private int type;

    @ApiModelProperty(example = "1")
    @ApiParam(value = "옵션 순서")
    @NotNull(message = "옵션의 순서를 설정해주세요")
    @Min(value = 1,message = "옵션의 순서는 최소 1부터 설정 가능합니다.")
    private int order;
}
