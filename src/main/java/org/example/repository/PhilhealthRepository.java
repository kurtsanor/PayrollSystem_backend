package org.example.repository;

import org.example.model.PhilhealthMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhilhealthRepository extends JpaRepository<PhilhealthMatrix,Integer> {
}
