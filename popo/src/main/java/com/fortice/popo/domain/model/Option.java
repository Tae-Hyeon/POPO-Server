package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "popo_id")
    private Popo popo;
//    @Column(name = "popo_id", nullable = false)
//    private int popoId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "`order`", nullable = false)
    private int order;

    public void printProperties(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(order);
    }

    @JsonIgnore
    public Integer getOwnerId(){
        return this.popo.getUser().getId();
    }
}
