package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.ProfileSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Returns Data Access by User Profile Summary Repository */
@Repository
public interface ProfileSummaryRepository extends JpaRepository<ProfileSummary, Long> {}
