package com.licnosoft.linkedin.util;

import com.licnosoft.linkedin.domain.users.Education;
import com.licnosoft.linkedin.domain.users.Skill;
import com.licnosoft.linkedin.domain.users.User;
import com.licnosoft.linkedin.domain.users.WorkExperience;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileUtil {

	public void updateSkills(User currentUser, User newUser) {
		// ----------- New Skills
		List<Skill> newSkills = newUser.getSkills();

		// ----------- Current Skills
		List<Skill> currentSkills = currentUser.getSkills();

		int newLength = newSkills.size();
		int currentLength = currentSkills.size();

		List<Skill> skillsToPersist = new ArrayList<>();

		boolean[] newFound = new boolean[newLength];
		boolean[] currentFound = new boolean[currentLength];

		// Compare everything to everything
		for (int i = 0; i < currentLength; i++) {
			Skill currentSkillsIter = currentSkills.get(i);
			for (int j = 0; j < newLength; j++) {
				Skill newSkillsIter = newSkills.get(j);

				if (Objects.equals(currentSkillsIter.getId(), newSkillsIter.getId())) {

					// if found => update

					setSkillUpdate(currentSkillsIter, newSkillsIter);
					skillsToPersist.add(currentSkillsIter);

					currentFound[i] = true;
					newFound[j] = true;

				}
			}
		}

		for (int i = 0; i < currentLength; i++) {
			if (currentFound[i]) continue;

			// if current not found => delete

			Skill currentSkillsIter = currentSkills.get(i);

			currentSkillsIter.setCreator(null);
			skillsToPersist.add(currentSkillsIter);

		}

		for (int j = 0; j < newLength; j++) {
			if (newFound[j]) continue;

			// if new-found and not in db => create

			Skill newSkillsIter = newSkills.get(j);
			newSkillsIter.setCreator(currentUser);
			newSkillsIter.setCreationDate(LocalDateTime.now());
			skillsToPersist.add(newSkillsIter);
		}

		currentUser.setSkills(skillsToPersist);

	}
	public void updateWorkExperiences(User currentUser,User newUser) {
		// ----------- New WorkExperiences
		List<WorkExperience> newWorkExperiences = newUser.getWorkExperiences();

		// ----------- Current WorkExperiences
		List<WorkExperience> currentWorkExperiences = currentUser.getWorkExperiences();

		int newLength = newWorkExperiences.size();
		int currentLength = currentWorkExperiences.size();

		List<WorkExperience> workExperiencesToPersist = new ArrayList<>();

		boolean[] newFound = new boolean[newLength];
		boolean[] currentFound = new boolean[currentLength];

		// Compare everything to everything
		for (int i = 0; i < currentLength; i++) {
			WorkExperience currentWorkExperiencesIter = currentWorkExperiences.get(i);
			for (int j = 0; j < newLength; j++) {
				WorkExperience newWorkExperiencesIter = newWorkExperiences.get(j);

				if (Objects.equals(currentWorkExperiencesIter.getId(), newWorkExperiencesIter.getId())) {

					// if found => update

					setWorkExperienceUpdate(currentWorkExperiencesIter, newWorkExperiencesIter);
					workExperiencesToPersist.add(currentWorkExperiencesIter);

					currentFound[i] = true;
					newFound[j] = true;

				}
			}
		}

		for (int i = 0; i < currentLength; i++) {
			if (currentFound[i]) continue;

			// if current not found => delete

			WorkExperience currentWorkExperiencesIter = currentWorkExperiences.get(i);

			currentWorkExperiencesIter.setCreator(null);
			workExperiencesToPersist.add(currentWorkExperiencesIter);

		}

		for (int j = 0; j < newLength; j++) {
			if (newFound[j]) continue;

			// if new-found and not in db => create

			WorkExperience newWorkExperiencesIter = newWorkExperiences.get(j);
			newWorkExperiencesIter.setCreator(currentUser);
			newWorkExperiencesIter.setCreationDate(LocalDateTime.now());
			workExperiencesToPersist.add(newWorkExperiencesIter);
		}

		currentUser.setWorkExperiences(workExperiencesToPersist);

	}
	public void updateEducations(User currentUser,User newUser) {
		// ----------- New Educations
		List<Education> newEducations = newUser.getEducations();

		// ----------- Current Educations
		List<Education> currentEducations = currentUser.getEducations();

		int newLength = newEducations.size();
		int currentLength = currentEducations.size();

		List<Education> educationsToPersist = new ArrayList<>();

		boolean[] newFound = new boolean[newLength];
		boolean[] currentFound = new boolean[currentLength];

		// Compare everything to everything
		for (int i = 0; i < currentLength; i++) {
			Education currentEducationsIter = currentEducations.get(i);
			for (int j = 0; j < newLength; j++) {
				Education newEducationsIter = newEducations.get(j);

				if (Objects.equals(currentEducationsIter.getId(), newEducationsIter.getId())) {

					// if found => update

					setEducationUpdate(currentEducationsIter, newEducationsIter);
					educationsToPersist.add(currentEducationsIter);

					currentFound[i] = true;
					newFound[j] = true;

				}
			}
		}

		for (int i = 0; i < currentLength; i++) {
			if (currentFound[i]) continue;

			// if current not found => delete

			Education currentEducationsIter = currentEducations.get(i);

			currentEducationsIter.setCreator(null);
			educationsToPersist.add(currentEducationsIter);

		}

		for (int j = 0; j < newLength; j++) {
			if (newFound[j]) continue;

			// if new-found and not in db => create

			Education newEducationsIter = newEducations.get(j);
			newEducationsIter.setCreator(currentUser);
			newEducationsIter.setCreationDate(LocalDateTime.now());
			educationsToPersist.add(newEducationsIter);
		}

		currentUser.setEducations(educationsToPersist);

	}
	private void setSkillUpdate(Skill currentSkillsIter, Skill newSkillsIter) {
		currentSkillsIter.setTitle(newSkillsIter.getTitle());
	}
	private void setWorkExperienceUpdate(WorkExperience currentWorkExperiencesIter, WorkExperience newWorkExperiencesIter) {
		currentWorkExperiencesIter.setTitle(newWorkExperiencesIter.getTitle());
		currentWorkExperiencesIter.setDescription(newWorkExperiencesIter.getDescription());
		currentWorkExperiencesIter.setCompany(newWorkExperiencesIter.getCompany());
		currentWorkExperiencesIter.setEmploymentType(newWorkExperiencesIter.getEmploymentType());
		currentWorkExperiencesIter.setWorkplaceType(newWorkExperiencesIter.getWorkplaceType());
		currentWorkExperiencesIter.setLocation(newWorkExperiencesIter.getLocation());
	}
	private void setEducationUpdate(Education currentEducationsIter, Education newEducationsIter) {
		currentEducationsIter.setTitle(newEducationsIter.getTitle());
		currentEducationsIter.setDescription(newEducationsIter.getDescription());
		currentEducationsIter.setOrganization(newEducationsIter.getOrganization());
		currentEducationsIter.setLocation(newEducationsIter.getLocation());
		currentEducationsIter.setDegree(newEducationsIter.getDegree());
		currentEducationsIter.setField(newEducationsIter.getField());
		currentEducationsIter.setGrade(newEducationsIter.getGrade());
		currentEducationsIter.setStartDate(newEducationsIter.getStartDate());
		currentEducationsIter.setEndDate(newEducationsIter.getEndDate());
	}


}
