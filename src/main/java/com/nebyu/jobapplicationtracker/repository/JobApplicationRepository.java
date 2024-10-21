package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    /**
     * Find all job applications for a specific user.
     */
    List<JobApplication> findByUser(User user);

    /**
     * Find job applications between a start date and an end date.
     */
    List<JobApplication> findByApplicationDateBetween(LocalDate startDate, LocalDate endDate);

    /**
     * Find job applications by status for a specific user.
     */
    List<JobApplication> findByStatusAndUser(String status, User user);

    /**
     * Count the number of job applications for a specific user.
     */
    int countJobApplicationsByUser(User user);
}
