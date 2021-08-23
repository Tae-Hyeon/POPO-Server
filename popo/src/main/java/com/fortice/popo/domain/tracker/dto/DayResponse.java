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
    int id;
    Date date;
    String image;
    List<OptionContentDTO> options;
}
