package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.Resume;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserProfile;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Workexperience Repo */
@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

  /**
   * Finds the reusmes based on IAM user id.
   *
   * @param keycloakId the IAM user id.
   * @return the {@link UserProfile}
   */
  @Query(value = "SELECT * FROM RESUME r WHERE r.key_cloak_id = :keyCloakId", nativeQuery = true)
  List<Resume> findByKeyCloakId(@Param("keyCloakId") String keycloakId);
}
