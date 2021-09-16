package com.fortice.popo.domain.tracker.dto;

import com.fortice.popo.domain.model.Day;
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

    public DayResponse(Day day, List<OptionContentDTO> options)
    {
        this.id = day.getId();
        this.date = day.getDate();
        this.image = day.getImage();
        this.options = options;
    }
    public DayResponse(Integer id, Date date, String image) {
        this.id = id;
        this.date = date;
        this.image = image;
    }
}
