package com.fortice.popo.domain.tracker.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OptionContentDTO {
    private Integer id;
    private String name;
    private String contents;
    private Integer type;
    private Integer order;
}
