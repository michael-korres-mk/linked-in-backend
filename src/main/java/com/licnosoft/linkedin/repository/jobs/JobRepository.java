package com.licnosoft.linkedin.repository.jobs;

import com.licnosoft.linkedin.domain.jobs.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long> {
	@Query("""
			SELECT j FROM Job j WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(j.title, '') || COALESCE(j.company, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
			""")
//			AND (j.enabled IS NULL OR j.enabled = :enabled)
	Page<Job> search(String searchTerm, Pageable pageable);
}
