package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

public class CommentDTO extends BasicEntityDTO {
	protected PlainEntityDTO creator;
	protected Long postId;
	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public Long getPostId() {return postId;}

	public void setPostId(Long postId) {this.postId = postId;}
}
