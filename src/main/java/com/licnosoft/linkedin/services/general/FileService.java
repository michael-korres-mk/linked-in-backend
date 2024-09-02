package com.licnosoft.linkedin.services.general;


import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.domain.general.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface FileService {

    Page<File> search(String searchTerm, FileType fileType, LocalDateTime createdFrom, LocalDateTime createdTo, Pageable pageable);

    Page<File> findAll(Pageable pageable);

    List<File> findAll();

    File findById(Long id);

    File findByPath(String filePath);

    File save(File entity);

    void deleteById(Long id);
    void updateFilePost(Long fileId, Long postId);

//    void updateFileOrganization(Long fileId, Long organizationId);

}
