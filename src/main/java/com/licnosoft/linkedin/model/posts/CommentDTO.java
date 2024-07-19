package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

public class CommentDTO extends BasicEntityDTO {
	protected PlainEntityDTO creator;

	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}
}
