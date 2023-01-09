package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Workexperience Repo */
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long> {}
