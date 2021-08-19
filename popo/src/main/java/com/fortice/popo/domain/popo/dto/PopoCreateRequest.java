package com.fortice.popo.domain.popo.dto;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PopoCreateRequest {
    private int category;
    private int type;
    private int order;
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
