package com.licnosoft.linkedin.model.users;

import com.licnosoft.linkedin.model.general.BasicEntityDTO;

import java.util.List;

public class UserProfileDTO extends UserBasicDTO {
	private boolean skillsEnabled;
	private boolean educationsEnabled;
	private boolean workExperiencesEnabled;
	protected List<BasicEntityDTO> skills;
	protected List<EducationDTO> educations;
	protected List<WorkExperienceDTO> workExperiences;

	public List<BasicEntityDTO> getSkills() {return skills;}

	public void setSkills(List<BasicEntityDTO> skills) {this.skills = skills;}

	public List<EducationDTO> getEducations() {return educations;}

	public void setEducations(List<EducationDTO> educations) {this.educations = educations;}

	public List<WorkExperienceDTO> getWorkExperiences() {return workExperiences;}

	public void setWorkExperiences(List<WorkExperienceDTO> workExperiences) {this.workExperiences = workExperiences;}

	public boolean isSkillsEnabled() {return skillsEnabled;}

	public void setSkillsEnabled(boolean skillsEnabled) {this.skillsEnabled = skillsEnabled;}

	public boolean isEducationsEnabled() {return educationsEnabled;}

	public void setEducationsEnabled(boolean educationsEnabled) {this.educationsEnabled = educationsEnabled;}

	public boolean isWorkExperiencesEnabled() {return workExperiencesEnabled;}

	public void setWorkExperiencesEnabled(boolean workExperiencesEnabled) {this.workExperiencesEnabled = workExperiencesEnabled;}
}
