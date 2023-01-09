package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.EducationExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Educational Experience Repo */
@Repository
public interface EducationExperienceRepository extends JpaRepository<EducationExperience, Long> {}
