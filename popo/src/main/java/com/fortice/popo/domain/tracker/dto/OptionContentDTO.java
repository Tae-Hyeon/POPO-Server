package com.fortice.popo.domain.tracker.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class OptionContentDTO {
    String name;
    String contents;
    Integer order;
}
