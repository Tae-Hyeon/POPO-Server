package com.fortice.popo.domain.popo.dao;

import com.fortice.popo.domain.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionDAO extends JpaRepository<Option, Integer> {
}
