package com.fortice.popo.domain.tracker.dao;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.tracker.dto.OptionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerOptionDAO extends JpaRepository<Option, Integer> {

//    @Query("SELECT id, name FROM options WHERE popo_id=:popoId ORDER BY `order`")
//    List<OptionDTO> findOptionsByPopoId(@Param("popoId") Integer popoId);
}
