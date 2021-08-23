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

    @Query("SELECT d.id as id, date, image, name, contents, `order`"
        +" FROM days as d, option_contents as c, options as o"
        +" WHERE d.id=:dayId AND d.id=c.day_id AND o.id=c.option_id"
        +" ORDER BY o.`order`")
    List<DayResponse> getDayResponseById(@Param("dayId") Integer dayId);
}
