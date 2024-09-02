package com.licnosoft.linkedin.model.general;


import com.licnosoft.linkedin.domain.enumeration.FileType;

public class FileDTO extends BasicEntityDTO {
    private FileType fileType;
    private String filePath;
    private Float size;

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Float getSize() {return size;}

    public void setSize(Float size) {this.size = size;}

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
