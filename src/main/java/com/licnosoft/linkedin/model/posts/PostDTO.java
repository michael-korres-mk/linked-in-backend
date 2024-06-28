package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;

import java.util.List;

public class PostDTO extends BasicEntityDTO {

	protected List<BasicEntityDTO> comments;

	public List<BasicEntityDTO> getComments() {return comments;}

	public void setComments(List<BasicEntityDTO> comments) {this.comments = comments;}
}
