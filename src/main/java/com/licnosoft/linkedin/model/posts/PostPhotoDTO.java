package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.FileDTO;

public class PostPhotoDTO extends EntityDTO {
	protected Integer ordering;
	protected FileDTO file;

	public Integer getOrdering() {return ordering;}

	public void setOrdering(Integer ordering) {this.ordering = ordering;}

	public FileDTO getFile() {return file;}

	public void setFile(FileDTO file) {this.file = file;}
}
