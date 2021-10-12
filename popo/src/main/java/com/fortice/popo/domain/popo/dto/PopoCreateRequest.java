package com.fortice.popo.domain.popo.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PopoCreateRequest {
    @ApiModelProperty(example = "1")
    @ApiParam(value = "포포 카테고리")
    @NotNull(message = "카테고리를 설정해주세요")
    @Min(value = 1, message = "카테고리는 최소 1부터 설정 가능합니다")
    @Max(value = 9, message = "카테고리는 최대 9까지 설정 가능합니다")
    private int category;

    @NotEmpty(message = "옵션을 최소 한 개 이상 설정해주세요")
    private List<OptionDTO> options;

    public boolean isOptionEmpty() {
        if(options.isEmpty())
            return true;
        else
            return false;
    }

    public List<Option> getOptions(Popo popo) {
        List<Option> returnOptions = new ArrayList<>();
        for(OptionDTO option : this.options) {
            Option newOption = Option.builder()
                    .popo(popo)
                    .name(option.getName())
                    .type(option.getType())
                    .order(option.getOrder())
                    .build();
            returnOptions.add(newOption);
        }
        return returnOptions;
    }
}
