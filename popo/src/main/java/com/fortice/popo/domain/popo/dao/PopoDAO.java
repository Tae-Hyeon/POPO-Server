package com.fortice.popo.domain.popo.dao;

import com.fortice.popo.domain.model.Option;
import com.fortice.popo.domain.model.Popo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PopoDAO extends JpaRepository<Popo, Integer> {
}
