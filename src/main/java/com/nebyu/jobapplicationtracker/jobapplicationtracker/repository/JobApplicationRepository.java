package com.nebyu.jobapplicationtracker.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.jobapplicationtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);
}
