package com.licnosoft.linkedin.repository.jobs;

import com.licnosoft.linkedin.domain.jobs.JobApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobApplicationRepository  extends JpaRepository<JobApplication, Long> {

	@Query("""
			SELECT ja FROM JobApplication ja WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(ja.job.title, '') || COALESCE(ja.job.company, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
			AND (:creatorId IS NULL OR ja.creator.id = :creatorId)
			ORDER BY ja.creationDate ASC
			""")
	Page<JobApplication> search(String searchTerm, Long creatorId, Pageable pageable);

	@Query("""
			SELECT ja FROM JobApplication ja WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(ja.job.title, '') || COALESCE(ja.job.company, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
			AND (:creatorId IS NULL OR ja.creator.id = :creatorId)
			ORDER BY ja.creationDate ASC
			""")
	List<JobApplication> searchAsList(String searchTerm, Long creatorId);

}
