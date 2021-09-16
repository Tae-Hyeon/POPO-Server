package com.fortice.popo.domain.tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateDayRequest {
    @NotNull
    Integer popoId;

    @NotBlank(message = "날짜를 설정해주세요 (ex. 2029-09-20)")
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$", message = "2021-09-21 형태의 날짜로 요청해주세요")
    String date;
}
