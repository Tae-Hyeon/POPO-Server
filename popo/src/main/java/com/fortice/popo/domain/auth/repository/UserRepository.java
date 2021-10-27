package com.fortice.popo.domain.auth.repository;

import com.fortice.popo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findByEmail(String email);
}
