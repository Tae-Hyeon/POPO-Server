package com.fortice.popo.domain.popo.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
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
@Getter @Setter
public class PopoCreateRequest {
    @NotNull(message = "카테고리를 설정해주세요")
    @Min(value = 1, message = "카테고리는 최소 1부터 설정 가능합니다")
    @Max(value = 9, message = "카테고리는 최대 9까지 설정 가능합니다")
    private int category;

    @Min(0)
    @Max(5)
    private int type;

    @NotNull(message = "순서를 설정해주세요")
    @Min(value = 1, message = "순서는 최소 1부터 설정 가능합니다")
    @Max(value = 9, message = "순서는 최대 9까지 설정 가능합니다")
    private int order;

    @NotEmpty(message = "옵션을 최소 한 개 이상 설정해주세요")
    private List<OptionDTO> options;

    public boolean isOptionEmpty() {
        if(options.isEmpty())
            return true;
        else
            return false;
    }

    public Popo getPopo() {
        Popo returnPopo = Popo.builder()
                .userId(1)
                .conceptId(1)
                .category(this.category)
                .type(this.type)
                .order(this.order)
                .build();
        return returnPopo;
    }

    public List<Option> getOptions(int popoId) {
        List<Option> returnOptions = new ArrayList<>();
        for(OptionDTO option : this.options)
        {
            Option newOption = Option.builder()
                    .popoId(popoId)
                    .name(option.getName())
                    .order(option.getOrder())
                    .build();
            returnOptions.add(newOption);
        }
        return returnOptions;
    }
}
