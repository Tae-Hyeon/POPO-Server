package com.fortice.popo.domain.popo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PopoDTO {
    private Integer id;

    private int category;

    private int order;

    private String background;

    public void setUri(String uri) {
        this.background = uri + this.background;
    }

    public PopoDTO(Popo popo) {
        this.id = popo.getId();
        this.category = popo.getCategory();
        this.order = popo.getOrder();
        this.background = popo.getBackground();
    }

    public PopoDTO(Popo popo, String uri) {
        this.id = popo.getId();
        this.category = popo.getCategory();
        this.order = popo.getOrder();
        this.background = uri + popo.getBackground();
    }
}
