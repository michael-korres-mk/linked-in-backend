package com.licnosoft.linkedin.repository.users;


import com.licnosoft.linkedin.domain.enumeration.UserRole;
import com.licnosoft.linkedin.domain.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String username,String password);

    Optional<User> findByUsername(String username);

    @Query("""
        SELECT u FROM User u WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(u.firstName, '') || COALESCE(u.lastName, '') || COALESCE(u.email, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
        AND ((cast(:firstName as string) IS NULL) OR (UPPER(COALESCE(u.firstName, '')) LIKE '%' || UPPER(cast(:firstName as string)) || '%'))
        AND ((cast(:lastName as string) IS NULL) OR (UPPER(COALESCE(u.lastName, '')) LIKE '%' || UPPER(cast(:lastName as string)) || '%'))
        AND ((cast(:email as string) IS NULL) OR (UPPER(COALESCE(u.email, '')) LIKE '%' || UPPER(cast(:email as string)) || '%'))
        AND ((:userRole IS NULL) OR (u.userRole  = :userRole))
        AND ((:loggedUserId IS NULL) OR (u.id != :loggedUserId))
        AND ((:enabled IS NULL) OR (u.enabled = :enabled))
    """)
    Page<User> search(@Param("searchTerm") String searchTerm,
                      @Param("firstName") String firstName,
                      @Param("lastName") String lastName,
                      @Param("email") String email,
                      @Param("userRole") UserRole userRole,
                      @Param("loggedUserId") Long loggedUserId,
                      @Param("enabled") Boolean enabled, Pageable pageable);

    @Query("""
        SELECT u FROM User u WHERE ((cast(:searchTerm as string) IS NULL) OR (UPPER(COALESCE(u.firstName, '') || COALESCE(u.lastName, '') || COALESCE(u.email, '')) LIKE '%' || UPPER(cast(:searchTerm as string)) || '%'))
        AND ((cast(:firstName as string) IS NULL) OR (UPPER(COALESCE(u.firstName, '')) LIKE '%' || UPPER(cast(:firstName as string)) || '%'))
        AND ((cast(:lastName as string) IS NULL) OR (UPPER(COALESCE(u.lastName, '')) LIKE '%' || UPPER(cast(:lastName as string)) || '%'))
        AND ((cast(:email as string) IS NULL) OR (UPPER(COALESCE(u.email, '')) LIKE '%' || UPPER(cast(:email as string)) || '%'))
        AND ((:userRole IS NULL) OR (u.userRole  = :userRole))
        AND ((:loggedUserId IS NULL) OR (u.id != :loggedUserId))
        AND ((:enabled IS NULL) OR (u.enabled = :enabled))
    """)
    List<User> asListSearch(@Param("searchTerm") String searchTerm,
                      @Param("firstName") String firstName,
                      @Param("lastName") String lastName,
                      @Param("email") String email,
                      @Param("userRole") UserRole userRole,
                      @Param("loggedUserId") Long loggedUserId,
                      @Param("enabled") Boolean enabled);

    Optional<User> findTopByOrderByIdAsc();

}
