package com.licnosoft.linkedin.repository.general;


import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.domain.general.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {

    @Query("SELECT a FROM File a WHERE (:searchTerm is null or a.filePath LIKE %:searchTerm%)" +
            " AND (:fileType is null or a.fileType = :fileTypeId)" +
            " AND (:createdFrom is null or a.creationDate >= :createdFrom) " +
            " AND (:createdTo is null or a.creationDate <= :createdTo)")
    Page<File> search(@Param("searchTerm") String searchTerm, @Param("fileType") FileType fileType,
                      @Param("createdFrom") LocalDateTime createdFrom, @Param("createdTo") LocalDateTime createdTo,
                      Pageable pageable);

    @Query("SELECT a FROM File a WHERE a.id = :id AND (:userId is null or a.creator IS NULL or a.creator.id = :userId)")
    Optional<File> findByIdAndUser(Long id, Long userId);

    @Query("SELECT a FROM File a WHERE a.filePath = :filePath AND (:userId is null or a.creator IS NULL or a.creator.id = :userId)")
    Optional<File> findByPathAndUser(String filePath, Long userId);

    @Modifying
    @Query("UPDATE File a SET a.post.id = :postId WHERE a.id = :fileId")
    void updateFilePost(@Param("fileId") Long fileId, @Param("postId") Long postId);

//    @Modifying
//    @Query("UPDATE File a SET a.organization.id = :organizationId WHERE a.id = :fileId")
//    void updateFileOrganization(@Param("fileId") Long fileId, @Param("organizationId") Long organizationId);
}
