package com.omarapi.studentapi.Repository;

import com.omarapi.studentapi.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Integer> {
}
