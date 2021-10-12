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

    @NotNull //생성은 null은 아니지만 ""로 비어있을 수 있다.
    private String contents;
}
