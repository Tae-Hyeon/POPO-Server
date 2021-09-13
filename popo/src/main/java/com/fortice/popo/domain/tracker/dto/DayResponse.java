package com.fortice.popo.domain.tracker.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DayResponse {
    private Integer id;
    private Date date;
    private String image;
    private List<OptionContentDTO> options;

    public DayResponse(Integer id, Date date, String image) {
        this.id = id;
        this.date = date;
        this.image = image;
    }
}
