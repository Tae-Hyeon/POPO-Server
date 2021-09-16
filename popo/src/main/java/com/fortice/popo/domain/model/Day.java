package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortice.popo.domain.tracker.dto.DayDTO;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.domain.tracker.dto.TrackerResponse;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(
        name = "DayResponseMapping",
        classes = @ConstructorResult(
                targetClass = DayResponse.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "date", type = Date.class),
                        @ColumnResult(name = "image", type = String.class)
                }
        )
)

@SqlResultSetMapping(
        name = "DayDTOMapping",
        classes = @ConstructorResult(
                targetClass = DayDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "date", type = String.class),
                        @ColumnResult(name = "image", type = String.class)
                }
        )
)
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "popo_id")
    private Popo popo;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "percent", nullable = false)
    private float percent;

    @Column(name = "image", nullable = false)
    private String image;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public Integer getOwnerId(){
        return this.popo.getUser().getId();
    }
}
