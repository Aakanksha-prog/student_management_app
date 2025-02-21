package com.manage.student.repository;

import com.manage.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
