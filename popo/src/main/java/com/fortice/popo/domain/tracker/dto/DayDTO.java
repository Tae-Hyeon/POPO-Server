package com.fortice.popo.domain.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayDTO {
    private Integer id;
    private Integer date;
    private String image;

    public DayDTO(Integer id, String date, String image) {
        this.id = id;
        this.date = Integer.parseInt(date);
        this.image = image;
    }
}
