package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
// Additional query methods if needed
}