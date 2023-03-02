package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.FileDB;
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
   * @return the file {@link FileDB}.
   */
  @Query(value = "SELECT * FROM files f WHERE f.keycloak_id = :keycloakId", nativeQuery = true)
  Optional<FileDB> findByKeycloakId(@Param("keycloakId") String keycloakId);

  /**
   * Gets the file based on user email id.
   *
   * @param email the IAM email of the user.
   * @return the resume {@link FileDB}.
   */
  @Query(value = "SELECT * FROM files f WHERE f.email = :email", nativeQuery = true)
  Optional<FileDB> findByEmailId(@Param("email") String email);
}
