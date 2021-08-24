package com.fortice.popo.domain.tracker.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class DayResponse {
    private int id;
    private Date date;
    private String image;
    private OptionContentDTO options;

    public DayResponse (int id, Date date, String image) {
        this.id = id;
        this.date = date;
        this.image = image;
    }
    public DayResponse (int id, Date date, String image, String name, String content, int order){
        this.id = id;
        this.date = date;
        this.image = image;
        this.options = OptionContentDTO.builder()
                .name(name)
                .content(content)
                .order(order)
                .build();
    }
}
