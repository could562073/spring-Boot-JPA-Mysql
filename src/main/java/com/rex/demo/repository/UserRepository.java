package com.rex.demo.repository;

import com.rex.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByAccount(String account);
    UserEntity findByAccount(String account);
}
