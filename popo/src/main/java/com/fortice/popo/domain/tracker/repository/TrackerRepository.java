package com.fortice.popo.domain.tracker.repository;

import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.tracker.dto.DayDTO;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrackerRepository extends JpaRepository<Day, Integer> {

    @Query(value = "SELECT new com.fortice.popo.domain.tracker.dto.DayDTO("
            +" d.id, SUBSTRING(d.date, 9, 2), d.image)"
            +" FROM Day d"
            +" WHERE d.popo.id=:popoId AND"
            +" SUBSTRING(d.date, 1, 7)=:dateFormat")
    List<DayDTO> getDayDTOById(@Param("popoId") Integer popoId, @Param("dateFormat") String dateFormat);

    @Query(value = "SELECT new com.fortice.popo.domain.tracker.dto.DayResponse("
        +" d.id, d.date, d.image)"
        +" FROM Day d"
        +" WHERE d.id=:dayId")
    DayResponse getDayResponseById(@Param("dayId") Integer dayId);

    @Query(value = "SELECT d"
            +" FROM Day d"
            +" WHERE d.popo.id=:popoId AND"
            +" d.date=:date")
    Optional<Day> findByPopoIdAndDate(Integer popoId, Date date);

    @Query(value = "SELECT d"
            +" FROM Day d"
            +" WHERE d.id = :dayId")
    Optional<Day> findByDayId(Integer dayId);

    Optional<Day> findByDate(Date date);
}
