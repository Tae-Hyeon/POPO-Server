package com.fortice.popo.domain.tracker.dto;

import com.fortice.popo.domain.model.Day;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayResponse {
    @ApiModelProperty(example = "7")
    @ApiParam(value = "날짜 ID")
    private Integer id;

    @ApiModelProperty(example = "2020-12-23T15:00:00.000+00:00")
    @ApiParam(value = "날짜(Date 형)")
    private Date date;

    @ApiModelProperty(example = "http://fortice.co.kr:8002/image/tracker/102312-30-filename.png")
    @ApiParam(value = "날짜 이미지")
    private String image;

    private List<OptionContentDTO> options;

    public void setUri(String uri)
    {
        if(!this.image.isBlank())
            this.image = uri + this.image;
    }

    public DayResponse(Day day, List<OptionContentDTO> options)
    {
        this.id = day.getId();
        this.date = new Date(day.getDate().getTime() + 9 * 60 * 60 * 1000);
        this.image = day.getImage();
        this.options = options;
    }

    public DayResponse(Integer id, Date date, String image) {
        this.id = id;
        this.date = new Date(date.getTime() + 9 * 60 * 60 * 1000);
        this.image = image;
    }
}
