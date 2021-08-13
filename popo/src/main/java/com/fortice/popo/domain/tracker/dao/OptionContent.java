package com.fortice.popo.domain.tracker.dao;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "option_contents")
public class OptionContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "option_id")
    //@Column(name = "option_id")
    private Option option;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    @Column(name = "contents")
    private String contents;
}
