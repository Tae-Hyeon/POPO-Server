package com.fortice.popo.domain.tracker.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackerResponse {
    @ApiModelProperty(example = "1")
    @ApiParam(value = "카테고리")
    private Integer category;

    @ApiModelProperty(example = "http://fortice.co.kr:8002/image/tracker/102312-30-filename.png")
    @ApiParam(value = "트래커 배경 이미지")
    private String background;

    @ApiParam(value = "사용자 이름")
    private List<DayDTO> tracker;

    public void updateTracker(String year, String month, List<DayDTO> tracker)
    {
        int lastDay = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1).lengthOfMonth();

        List<DayDTO> emptySettedTracker = new ArrayList<>();

        for(int i = 1; i <= lastDay; i++) {
            emptySettedTracker.add(
                    DayDTO.builder()
                    .id(-1)
                    .date(i)
                    .image("")
                    .build()
            );
        }

        if(tracker != null) {
            for (DayDTO day : tracker)
                emptySettedTracker.set(day.getDate() - 1, day);
        }

        this.tracker = emptySettedTracker;
    }
}
