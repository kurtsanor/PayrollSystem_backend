package org.example.repository;

import org.example.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    List<Attendance> findByEmployeeId(int id);

    @Query("SELECT a FROM Attendance a WHERE a.employee.id = :id AND FUNCTION('MONTH', a.date) = :month AND FUNCTION('YEAR', a.date) = :year")
    List<Attendance> findByEmployeeIdAndMonthAndYear(@Param("id") int id, @Param("month") int month, @Param("year") int year);
}
