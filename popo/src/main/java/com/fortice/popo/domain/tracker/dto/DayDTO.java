package com.fortice.popo.domain.tracker.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayDTO {
    @ApiModelProperty(example = "7")
    @ApiParam(value = "날짜 ID")
    private Integer id;

    @ApiModelProperty(example = "1")
    @ApiParam(value = "날짜(일)")
    private Integer date;

    @ApiModelProperty(example = "http://fortice.co.kr:8002/image/day/102312-30-filename.png")
    @ApiParam(value = "날짜 이미지")
    private String image;

    public void setUri(String uri) {
        if(!this.image.isBlank())
            this.image = uri + this.image;
    }

    public DayDTO(Integer id, String date, String image) {
        this.id = id;
        this.date = Integer.parseInt(date);
        this.image = image;
    }
}
