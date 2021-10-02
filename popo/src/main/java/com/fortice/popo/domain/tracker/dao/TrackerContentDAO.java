package com.fortice.popo.domain.tracker.dao;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.OptionContent;
import com.fortice.popo.domain.tracker.dto.OptionContentDTO;
import com.fortice.popo.domain.tracker.dto.OptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerContentDAO extends JpaRepository<OptionContent, Integer> {

    @Query("SELECT new com.fortice.popo.domain.tracker.dto.OptionContentDTO(" +
            "c.id, c.option.name, c.contents, c.option.type, c.option.order) " +
            "FROM OptionContent c " +
            "WHERE c.day.id=:dayId " +
            "ORDER BY c.option.order")
    List<OptionContentDTO> findOptionsByDayId(@Param("dayId") Integer dayId);
}
