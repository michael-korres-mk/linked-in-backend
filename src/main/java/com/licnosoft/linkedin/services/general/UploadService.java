package com.licnosoft.linkedin.services.general;

import com.licnosoft.linkedin.model.general.FileDTO;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadFile(MultipartFile file);

    FileDTO uploadFile(MultipartFile file, String fileType,Long postId);

    Resource getFile(Long id);

    Resource getInstructionManual();
}
