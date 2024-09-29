package com.licnosoft.linkedin.util;

import com.licnosoft.linkedin.domain.jobs.Job;
import com.licnosoft.linkedin.domain.users.Skill;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobUtil {

	public void updateSkills(Job currentJob, Job newJob) {
		// ----------- New Skills
		List<Skill> newSkills = newJob.getSkills();

		// ----------- Current Skills
		List<Skill> currentSkills = currentJob.getSkills();

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

			currentSkillsIter.setJob(null);
			skillsToPersist.add(currentSkillsIter);

		}

		for (int j = 0; j < newLength; j++) {
			if (newFound[j]) continue;

			// if new-found and not in db => create

			Skill newSkillsIter = newSkills.get(j);
			newSkillsIter.setJob(currentJob);
			newSkillsIter.setCreationDate(LocalDateTime.now());
			skillsToPersist.add(newSkillsIter);
		}

		currentJob.setSkills(skillsToPersist);

	}

	private void setSkillUpdate(Skill currentSkillsIter, Skill newSkillsIter) {
		currentSkillsIter.setTitle(newSkillsIter.getTitle());
	}

}
