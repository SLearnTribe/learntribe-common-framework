package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.SideProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Workexperience Repo */
@Repository
public interface SideProjectRepository extends JpaRepository<SideProject, Long> {}
