package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.ResumeSideProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Workexperience Repo */
@Repository
public interface ResumeSideProjectRepository extends JpaRepository<ResumeSideProject, Long> {}
