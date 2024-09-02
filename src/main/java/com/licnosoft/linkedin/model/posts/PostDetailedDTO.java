package com.licnosoft.linkedin.model.posts;

import java.util.List;

public class PostDetailedDTO extends PostBasicDTO {
	protected List<PostPhotoDTO> photos;

	public List<PostPhotoDTO> getPhotos() {return photos;}

	public void setPhotos(List<PostPhotoDTO> photos) {this.photos = photos;}
}
