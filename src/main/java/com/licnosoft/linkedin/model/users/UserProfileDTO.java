package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;

import java.util.List;

public class UserProfileDTO extends UserBasicDTO {

	protected List<BasicEntityDTO> skills;
	protected List<EducationDTO> educations;
	protected List<WorkExperienceDTO> workExperiences;

	public List<BasicEntityDTO> getSkills() {return skills;}

	public void setSkills(List<BasicEntityDTO> skills) {this.skills = skills;}

	public List<EducationDTO> getEducations() {return educations;}

	public void setEducations(List<EducationDTO> educations) {this.educations = educations;}

	public List<WorkExperienceDTO> getWorkExperiences() {return workExperiences;}

	public void setWorkExperiences(List<WorkExperienceDTO> workExperiences) {this.workExperiences = workExperiences;}
}
