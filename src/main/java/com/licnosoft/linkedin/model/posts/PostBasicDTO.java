package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.util.List;

public class PostBasicDTO extends BasicEntityDTO {
	protected PlainEntityDTO creator;
	protected List<CommentDTO> comments;
	protected List<LikeDTO> likes;

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public List<CommentDTO> getComments() { return comments; }

	public void setComments(List<CommentDTO> comments) { this.comments = comments; }

	public List<LikeDTO> getLikes() {return likes;}

	public void setLikes(List<LikeDTO> likes) {this.likes = likes;}
}
