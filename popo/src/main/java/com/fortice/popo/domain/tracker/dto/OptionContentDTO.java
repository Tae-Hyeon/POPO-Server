package com.fortice.popo.domain.tracker.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.OptionContent;
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

    public OptionContentDTO(Option option, OptionContent content) {
        this.id = content.getId();
        this.name = option.getName();
        this.contents = content.getContents();
        this.type = option.getType();
        this.order = option.getOrder();
    }
}
