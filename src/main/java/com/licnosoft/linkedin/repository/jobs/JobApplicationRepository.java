package com.licnosoft.linkedin.repository.jobs;

import com.licnosoft.linkedin.domain.jobs.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository  extends JpaRepository<JobApplication, Long> {
}
