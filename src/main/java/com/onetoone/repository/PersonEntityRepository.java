package com.onetoone.repository;

import com.onetoone.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
}