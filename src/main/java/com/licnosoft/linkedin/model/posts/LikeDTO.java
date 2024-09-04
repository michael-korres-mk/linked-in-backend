package com.licnosoft.linkedin.model.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.licnosoft.linkedin.config.Constants;
import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.general.PlainEntityDTO;

import java.time.LocalDateTime;

public class LikeDTO extends EntityDTO {

	protected PlainEntityDTO creator;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = Constants.APP_TIMEZONE)
	protected LocalDateTime creationDate;
	public PlainEntityDTO getCreator() {return creator;}

	public void setCreator(PlainEntityDTO creator) {this.creator = creator;}

	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
}
