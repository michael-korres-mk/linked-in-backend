package com.licnosoft.linkedin.model.posts;

import com.licnosoft.linkedin.config.LocalDateTimeAdapter;
import com.licnosoft.linkedin.model.general.EntityDTO;
import com.licnosoft.linkedin.model.users.UserBasicDTO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "like")
public class LikeDTO extends EntityDTO {

	protected UserBasicDTO creator;
	protected LocalDateTime creationDate;

	public UserBasicDTO getCreator() {return creator;}

	public void setCreator(UserBasicDTO creator) {this.creator = creator;}

	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlElement(name = "creation-date")
	public LocalDateTime getCreationDate() {return creationDate;}

	public void setCreationDate(LocalDateTime creationDate) {this.creationDate = creationDate;}
}
