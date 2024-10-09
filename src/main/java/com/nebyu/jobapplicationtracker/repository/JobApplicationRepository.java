package com.nebyu.jobapplicationtracker.repository;

import com.nebyu.jobapplicationtracker.model.JobApplication;
import com.nebyu.jobapplicationtracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByUser(User user);



    @Query("SELECT j FROM JobApplication j WHERE j.status = ?1 AND j.user = ?2")
    List<JobApplication> findByStatusAndUser(String status, User user);



    @Query("SELECT j FROM JobApplication j WHERE j.applicationDate BETWEEN ?1 AND ?2")
    List<JobApplication> findByDateRange(LocalDate startDate, LocalDate endDate);


    @Query("SELECT COUNT(j) FROM JobApplication j WHERE j.user = ?1")
    int countJobApplicationsByUser(User user);


}
