package com.fortice.popo.domain.tracker.dao;

import com.fortice.popo.domain.model.Day;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrackerDAO extends JpaRepository<Day, Integer> {

    @Query(value = "SELECT new com.fortice.popo.domain.tracker.dto.DayResponse("
        +" d.id, d.date, d.image)"
        +" FROM Day d"
        +" WHERE d.id=:dayId")
    DayResponse getDayResponseById(@Param("dayId") Integer dayId);
}
