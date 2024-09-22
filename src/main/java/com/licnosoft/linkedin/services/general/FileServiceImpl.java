package com.licnosoft.linkedin.services.general;

import com.licnosoft.linkedin.config.exceptions.AppNotFoundException;
import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.domain.general.File;
import com.licnosoft.linkedin.repository.general.FileRepository;
import com.licnosoft.linkedin.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final FileRepository entityRepository;
    private final UserService userService;

    @Autowired
    public FileServiceImpl(FileRepository entityRepository, UserService userService) {
        this.entityRepository = entityRepository;
        this.userService = userService;
    }

    @Override
    public Page<File> search(String searchTerm, FileType fileType, LocalDateTime createdFrom, LocalDateTime createdTo, Pageable pageable) {
        return entityRepository.search(searchTerm, fileType, createdFrom, createdTo, pageable);
    }

    @Override
    public Page<File> findAll(Pageable pageable) {
        return entityRepository.findAll(pageable);
    }

    @Override
    public List<File> findAll() {
        return entityRepository.findAll();
    }

    @Override
    public File findById(Long id) {
        return entityRepository.findById(id).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αρχείο με αυτό το id: " + id +"."));
        //        return entityRepository.findByIdAndUser(id, userService.getLoggedUser().getId()).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αρχείο με αυτό το id: " + id +"."));
    }

    @Override
    public File findByPath(String filePath) {
        return entityRepository.findByPathAndUser(filePath, userService.getLoggedUser().getId()).orElseThrow(() -> new AppNotFoundException("Δε βρέθηκε αρχείο με αυτό το path: " + filePath +"."));
    }

    @Override
    public File save(File entity) {
        return entityRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        entityRepository.deleteById(id);
    }

//	@Override
//	public void updateFilePost(Long fileId, Long postId) {
//        entityRepository.updateFilePost(fileId, postId);
//	}

	//    @Override
//    public void updateFileOrganization(Long fileId, Long organizationId) {
//        entityRepository.updateFileOrganization(fileId, organizationId);
//    }
}
