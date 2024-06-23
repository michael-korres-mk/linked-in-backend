package com.licnosoft.linkedin.repository.users;


import com.licnosoft.linkedin.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String username,String password);

    Optional<User> findByUsername(String username);

    @Query("SELECT a FROM User a " +
            "WHERE (:searchTerm IS NULL) " +
//          "OR UPPER(COALESCE(a.firstName, '') || COALESCE(a.lastName, '') || COALESCE(a.email, '')) LIKE '%' || UPPER(:searchTerm) || '%') " +
            "AND (:enabled IS NULL OR a.enabled = :enabled)")
    Page<User> search(@Param("searchTerm") String searchTerm, @Param("enabled") Boolean enabled, Pageable pageable);


    Optional<User> findTopByOrderByIdAsc();

}
