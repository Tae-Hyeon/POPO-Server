package com.fortice.popo.domain.tracker.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TrackerResponse {
    private Integer category;
    private List<DayDTO> tracker;

    public void updateTracker(int lastDay, List<DayDTO> tracker)
    {
        DayDTO emptyDay = DayDTO.builder()
                .id(null)
                .image("")
                .build();
        List<DayDTO> emptySettedTracker = new ArrayList<>();

        for(int i = 1; i <= lastDay; i++)
        {
            emptyDay.setDate(i);
            emptySettedTracker.add(emptyDay);
        }

        for(DayDTO day : tracker)
            emptySettedTracker.set(day.getDate() - 1, day);

        this.tracker = emptySettedTracker;
    }
}
