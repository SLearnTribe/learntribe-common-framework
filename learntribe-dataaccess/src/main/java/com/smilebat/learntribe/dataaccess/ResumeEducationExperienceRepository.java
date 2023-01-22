package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.ResumeEducationExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Educational Experience Repo */
@Repository
public interface ResumeEducationExperienceRepository
    extends JpaRepository<ResumeEducationExperience, Long> {}
