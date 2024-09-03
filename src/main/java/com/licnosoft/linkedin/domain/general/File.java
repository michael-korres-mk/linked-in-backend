package com.licnosoft.linkedin.domain.general;


import com.licnosoft.linkedin.config.ValidationErrorMessages;
import com.licnosoft.linkedin.domain.enumeration.FileType;
import com.licnosoft.linkedin.domain.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "files")
public class File implements Serializable, PlainEntity<File> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    @NotBlank(message = ValidationErrorMessages.TITLE_NOT_BLANK)
    private String title;

    @Column(name = "file_path")
    @NotBlank(message = ValidationErrorMessages.URL_NOT_BLANK)
    private String filePath;

    @Enumerated(EnumType.STRING)
    @NotNull(message = ValidationErrorMessages.FILE_TYPE_NOT_BLANK)
    @Column(name = "file_type")
    private FileType fileType;

    @Column(name = "size")
    private Long size;

    @Column(name = "creation_date")
    @NotNull(message = ValidationErrorMessages.CREATION_NOT_BLANK)
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @NotNull(message = ValidationErrorMessages.CREATOR_NOT_BLANK)
    private User creator;

    public File() {}

    public File(String title, String filePath, FileType fileType, Long size, User creator) {
        this.title = title;
        this.filePath = filePath;
        this.fileType = fileType;
        this.size = size;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String fileName) {
        this.filePath = fileName;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User user) {
        this.creator = user;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        File entity = (File) o;
        if (entity.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "File{"
                + "id=" + id
                + ", fileName=" + filePath
                + '}';
    }
}
