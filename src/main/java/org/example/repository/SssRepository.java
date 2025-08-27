package org.example.repository;

import org.example.model.SssMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SssRepository extends JpaRepository<SssMatrix, Integer> {
}
