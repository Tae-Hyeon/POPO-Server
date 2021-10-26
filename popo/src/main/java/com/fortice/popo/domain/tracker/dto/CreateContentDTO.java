package com.fortice.popo.domain.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateContentDTO {
    @NotNull
    private Integer optionId;

    private String contents;
}
