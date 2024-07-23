package com.licnosoft.linkedin.repository.users;


import com.licnosoft.linkedin.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String username,String password);

    Optional<User> findByUsername(String username);

    @Query("""
      SELECT u FROM User u WHERE (cast(:searchTerm as string) IS NULL)
      OR (UPPER(COALESCE(u.firstName, '') || COALESCE(u.lastName, '') || COALESCE(u.email, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%')
      AND (:enabled IS NULL OR u.enabled = :enabled)
    """)
    Page<User> search(@Param("searchTerm") String searchTerm, @Param("enabled") Boolean enabled, Pageable pageable);


    Optional<User> findTopByOrderByIdAsc();

}
