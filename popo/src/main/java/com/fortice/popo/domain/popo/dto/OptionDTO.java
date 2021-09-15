package com.fortice.popo.domain.popo.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter @Setter
public class OptionDTO {
    @NotBlank(message = "옵션의 이름을 설정해주세요")
    @Length(min=0, max=30)
    private String name;

    @NotNull(message = "옵션의 타입을 설정해주세요(String: 1, INT: 2")
    @Min(0)
    @Max(5)
    private int type;

    @NotNull(message = "옵션의 순서를 설정해주세요")
    @Min(value = 1,message = "옵션의 순서는 최소 1부터 설정 가능합니다.")
    private int order;
}
