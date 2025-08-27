package org.example.repository;

import org.example.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByEmployeeId(int id);

    @Query(
        value = "SELECT * FROM attendance a WHERE a.employee_id = :id AND EXTRACT(MONTH FROM a.date) = :month AND EXTRACT(YEAR FROM a.date) = :year",
        nativeQuery = true
    )
    List<Attendance> findByEmployeeIdAndMonthAndYear(@Param("id") int id, @Param("month") int month, @Param("year") int year);
}
