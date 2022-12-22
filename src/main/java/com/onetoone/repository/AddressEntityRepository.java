package com.onetoone.repository;

import com.onetoone.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity, Long> {
}