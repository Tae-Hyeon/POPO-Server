package com.fortice.popo.domain.tracker.dto;

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
    private Integer category;
    private List<DayDTO> tracker;

    public void updateTracker(String year, String month, List<DayDTO> tracker)
    {
        int lastDay = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1).lengthOfMonth();

        List<DayDTO> emptySettedTracker = new ArrayList<>();

        for(int i = 1; i <= lastDay; i++) {
            emptySettedTracker.add(
                    DayDTO.builder()
                    .id(null)
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
