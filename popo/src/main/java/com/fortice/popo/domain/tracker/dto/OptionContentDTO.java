package com.fortice.popo.domain.tracker.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.OptionContent;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionContentDTO {
    @ApiModelProperty(example = "1")
    @ApiParam(value = "컨텐츠 ID")
    private Integer id;

    @ApiModelProperty(example = "제목")
    @ApiParam(value = "옵션 이름")
    private String name;

    @ApiModelProperty(example = "아이언맨")
    @ApiParam(value = "컨텐츠")
    private String contents;

    @ApiModelProperty(example = "0")
    @ApiParam(value = "옵션 타입(String: 0, Integer: 1)")
    private Integer type;

    @ApiModelProperty(example = "1")
    @ApiParam(value = "옵션 순서")
    private Integer order;

    public OptionContentDTO(Option option, OptionContent content) {
        this.id = content.getId();
        this.name = option.getName();
        this.contents = content.getContents();
        this.type = option.getType();
        this.order = option.getOrder();
    }
}
