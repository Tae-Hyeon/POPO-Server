package com.fortice.popo.domain.popo.repository;

import com.fortice.popo.domain.model.Popo;
import com.fortice.popo.domain.popo.dto.PopoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopoRepository extends JpaRepository<Popo, Integer> {

    @Query(value = "SELECT new com.fortice.popo.domain.popo.dto.PopoDTO" +
            "(p.id, p.category, p.order, p.background) " +
            "FROM Popo p " +
            "WHERE p.user.id=:userId")
    List<PopoDTO> findPoposByUserId(Integer userId);
}
