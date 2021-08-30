package com.fortice.popo.domain.tracker.dto;

import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackerResponse {
    private Integer id;
    private Integer date;
    private String image;

    public TrackerResponse(Integer id, String date, String image) {
        this.id = id;
        this.date = Integer.parseInt(date);
        this.image = image;
    }
}
