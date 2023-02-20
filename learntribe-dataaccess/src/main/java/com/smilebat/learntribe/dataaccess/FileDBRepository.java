package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.FileDB;
import com.smilebat.learntribe.dataaccess.jpa.entity.ProfileSummary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by User Resume Uploads Repository */
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
  /**
   * Gets the file based on user id.
   *
   * @param keycloakId the IAM of the user.
   * @return summaries {@link ProfileSummary}.
   */
  @Query(value = "SELECT * FROM files f WHERE f.keycloak_id = :keycloakId", nativeQuery = true)
  Optional<FileDB> findByKeycloakId(@Param("keycloakId") String keycloakId);
}
