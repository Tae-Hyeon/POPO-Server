package com.fortice.popo.domain.model;

import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(
        name = "OptionContentDTOMapping",
        classes = @ConstructorResult(
                targetClass = OptionContentDTO.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "contents", type = String.class),
                        @ColumnResult(name = "order", type = Integer.class)
                }
        )
)

@Data
@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
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
