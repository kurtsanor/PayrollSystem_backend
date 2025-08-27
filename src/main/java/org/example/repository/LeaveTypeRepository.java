package org.example.repository;

import org.example.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{
    LeaveType findByType(String type);
}
