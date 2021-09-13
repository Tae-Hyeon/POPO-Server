package com.fortice.popo.domain.popo.dao;

import com.fortice.popo.domain.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionDAO extends JpaRepository<Option, Integer> {
    @Query(value = "SELECT o FROM Option o WHERE o.popo.id=:popoId")
    List<Option> getIdsByPopo(@Param("popoId") Integer popoId);
}
