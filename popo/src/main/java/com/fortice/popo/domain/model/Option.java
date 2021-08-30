package com.fortice.popo.domain.model;

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

    @ManyToOne
    @JoinColumn(name = "popo_id")
    private Popo popo;
//    @Column(name = "popo_id", nullable = false)
//    private int popoId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "`order`", nullable = false)
    private int order;

    public void printProperties(){
        System.out.println(id);
        System.out.println(name);
        System.out.println(order);
    }
}
