package com.fortice.popo.domain.popo.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
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
    @Min(value = 1, message = "요청 url의 최소값은 0입니다.")
    //@Pattern(regexp = "^[1-9]+%", message = "숫자만 입력 가능합니다")
    private int id;

    @NotNull(message = "카테고리를 설정해주세요")
    @Min(value = 1, message = "카테고리는 최소 1부터 설정 가능합니다")
    @Max(value = 9, message = "카테고리는 최대 9까지 설정 가능합니다")
    private int category;
//
//    @NotNull(message = "순서를 설정해주세요")
//    @Min(value = 1, message = "순서는 최소 1부터 설정 가능합니다")
//    @Max(value = 9, message = "순서는 최대 9까지 설정 가능합니다")
//    private int order;

    @NotEmpty(message = "옵션을 최소 한 개 이상 설정해주세요")
    private List<OptionDTO> options;

    public boolean isOptionEmpty() {
        if(options.isEmpty())
            return true;
        else
            return false;
    }

//    public Popo getPopo(int popoId) {
//        Popo returnPopo = Popo.builder()
//                .id(popoId)
//                .user(User.builder().id(1).build())
//                .conceptId(1)
//                .category(this.category)
//                .order(this.order)
//                .build();
//        return returnPopo;
//    }

    public List<Option> getOptions(Popo popo) {
        List<Option> returnOptions = new ArrayList<>();
        for(OptionDTO option : this.options)
        {
            Option newOption = Option.builder()
                    .popo(popo)
                    .name(option.getName())
                    .order(option.getOrder())
                    .build();
            returnOptions.add(newOption);
        }
        return returnOptions;
    }
}
