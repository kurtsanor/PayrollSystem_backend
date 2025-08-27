package org.example.repository;

import org.example.model.TaxMatrix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<TaxMatrix, Integer> {
}
