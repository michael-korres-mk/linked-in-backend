package com.licnosoft.linkedin.controllers.general;


import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.model.general.FileDTO;
import com.licnosoft.linkedin.model.general.PlainListDTO;
import com.licnosoft.linkedin.model.users.UserDetailedDTO;
import com.licnosoft.linkedin.services.general.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {

    private final UploadService entityService;

    @Autowired
    public FileController(UploadService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/types/list")
    public ResponseEntity<List<PlainListDTO>> getPlainList() {
        return new ResponseEntity<>(Arrays.stream(FileType.values())
                .map(type -> new PlainListDTO(type.name(), type.name().toLowerCase())).toList(), HttpStatus.OK);
    }

    @PostMapping(path ="/users-download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getUsersFile(@RequestBody List<UserDetailedDTO> userDTOs, @RequestParam Boolean xml) {
        return new ResponseEntity<>(entityService.getUsersFile(userDTOs,xml), HttpStatus.OK);
    }

//    @GetMapping(path  = "/instruction-manual", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public ResponseEntity<Resource> getInstructionManual() {
//        return new ResponseEntity<>(entityService.getInstructionManual(), HttpStatus.OK);
//    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        return new ResponseEntity<>(entityService.getFile(id), HttpStatus.OK);
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDTO> fileUpload(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "type", required = false, defaultValue = "jpg") String fileType,@RequestParam(name = "post", required = false) Long postId) {
        return new ResponseEntity<>(entityService.uploadFile(file, fileType,postId), HttpStatus.OK);
    }
}