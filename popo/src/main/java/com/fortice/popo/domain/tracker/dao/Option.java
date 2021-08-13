package com.fortice.popo.domain.tracker.dao;

import com.fortice.popo.domain.popo.dao.Popo;
import lombok.*;
import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "options")
public class Option {
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "popo_id")
    //@Column(name = "popo_id", nullable = false)
    private Popo popo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order", nullable = false)
    private Integer order;
}
