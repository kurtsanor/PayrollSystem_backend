package org.example.repository;

import org.example.model.AllowanceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AllowanceTypeRepository extends JpaRepository<AllowanceType, Integer> {
    Optional<AllowanceType> findByName(String name);
}
