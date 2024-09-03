package com.licnosoft.linkedin.services.general;

import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.config.exceptions.AppRuntimeException;
import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.domain.general.File;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.model.general.FileDTO;
import com.licnosoft.linkedin.services.posts.PostService;
import com.licnosoft.linkedin.services.users.UserService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.UUID;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

    private static final Logger log = LoggerFactory.getLogger(UploadServiceImpl.class);
    private final ResourceLoader resourceLoader;
    private final FileService fileService;
    private final UserService userService;
    private final PostService postService;
    private final ModelMapper modelMapper;
    private @Value("${app.apiurl}")
    String apiUrl;
    private Path fileStorageLocation;

    public UploadServiceImpl(ResourceLoader resourceLoader, FileService fileService, UserService userService, PostService postService, ModelMapper modelMapper) {
        this.resourceLoader = resourceLoader;
        this.fileService = fileService;
        this.userService = userService;
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.fileStorageLocation = Paths.get("./" + Constants.UPLOAD_FOLDER).toAbsolutePath().normalize();
        Path instructionManualPath = Paths.get("./" + Constants.UPLOAD_FOLDER + "instruction-manual");
        try {
            if (!Files.exists(this.fileStorageLocation)) {
                Files.createDirectories(this.fileStorageLocation);
            }
            if (!Files.exists(instructionManualPath)) {
                Files.createDirectories(instructionManualPath);
            }
        } catch (IOException e) {
            String msg = "Could not create the upload file directory, " + e.getMessage();
            log.error(msg);
            throw new AppRuntimeException(msg);
        }
    }

    @Override
    public String uploadFile(MultipartFile file) {
        saveLocalFile(file, file.getOriginalFilename());
        return generateFileUrl(file.getOriginalFilename());
    }

    @Override
    public FileDTO uploadFile(MultipartFile file, String fileType,Long postId) {
        String fileName = generateFileName(fileType);
        User user = userService.getLoggedUser();
        // First save the file in the database in order to prevent cleaning if failed to save locally
        File fileEntity = fileService.save(new File(file.getOriginalFilename(), fileName, FileType.valueOf(fileType.toUpperCase()), file.getSize(), user));
        saveLocalFile(file, fileName);
        log.info("File uploaded successfully, {}", fileEntity.getTitle());
        return modelMapper.map(fileEntity, FileDTO.class);
    }

    public void saveLocalFile(MultipartFile multipartFile, String fileName) {
        try {
            saveLocalFile(multipartFile.getInputStream(), fileName);
        } catch (IOException e) {
            String msg = "Could not upload file, " + e.getMessage();
            log.error(msg);
            throw new AppRuntimeException(msg);
        }
    }

    public void saveLocalFile(InputStream is, String fileName) {
        String directory = createOrFetchDirectory();
        Path uploadPath = this.fileStorageLocation.resolve(directory + fileName);
        try {
            if (Files.exists(uploadPath)) {
                throw new AppRuntimeException(ValidationErrorMessages.FILE_ALREADY_EXISTS);
            }
            Files.copy(is, uploadPath);
        } catch (IOException e) {
            String msg = "Could not copy uploaded file, " + e.getMessage();
            log.error(msg);
            throw new AppRuntimeException(msg);
        }
    }

    @Override
    public Resource getFile(Long id) {
        File file = fileService.findById(id);
        // Get the month and the year of the file creation date
        int monthValue = file.getCreationDate().getMonthValue();
        int year = file.getCreationDate().getYear();
        String fullPath = Constants.DIRECTORY_SLASH + year + Constants.DIRECTORY_SLASH + monthValue + Constants.DIRECTORY_SLASH + file.getFilePath();
        return resourceLoader.getResource("file:" + Paths.get("./" + Constants.UPLOAD_FOLDER).toAbsolutePath() + fullPath);
    }

    @Override
    public Resource getInstructionManual() {
        String instructionManualPath = "./" + Constants.UPLOAD_FOLDER + "instruction-manual";
        return resourceLoader.getResource("file:" + Paths.get(instructionManualPath + "/manual.pdf"));
    }

    private String generateFileUrl(String fileName) {
        return apiUrl + Constants.FILES_ENDPOINT + fileName;
    }

    private String createOrFetchDirectory() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        Path uploadPath = this.fileStorageLocation.resolve(year + Constants.DIRECTORY_SLASH + month + Constants.DIRECTORY_SLASH);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("Created directory: {}", uploadPath);
            }
        } catch (IOException e) {
            String msg = "Could not create the directory where the uploaded files will be stored, " + e.getMessage();
            log.error(msg);
            throw new AppRuntimeException(msg);
        }
        return uploadPath.toFile().getAbsolutePath() + "/";
    }

    private String generateFileName(String fileType) {
        return UUID.randomUUID() + "." + fileType;
    }
}
