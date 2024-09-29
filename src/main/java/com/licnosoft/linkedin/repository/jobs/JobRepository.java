package com.licnosoft.linkedin.repository.jobs;

import com.licnosoft.linkedin.domain.jobs.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
	@Query("""
			SELECT j FROM Job j WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(j.title, '') || COALESCE(j.company, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
			AND (:creatorId IS NULL OR j.creator.id = :creatorId)
			 AND ((:loggedUserId IS NULL) OR (j.creator.id != :loggedUserId))
			 ORDER BY j.creationDate ASC
			""")
	Page<Job> search(String searchTerm,Long loggedUserId,Long creatorId, Pageable pageable);

	@Query("""
			SELECT j FROM Job j WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(j.title, '') || COALESCE(j.company, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
			AND (:creatorId IS NULL OR j.creator.id = :creatorId)
            AND ((:loggedUserId IS NULL) OR (j.creator.id != :loggedUserId))
			ORDER BY j.creationDate ASC
			""")
	List<Job> searchAsList(String searchTerm,Long loggedUserId, Long creatorId);
}
