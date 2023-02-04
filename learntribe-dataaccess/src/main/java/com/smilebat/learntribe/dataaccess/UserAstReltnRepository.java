package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.Assessment;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserAstReltn;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by User Assessment Repo */
@Repository
public interface UserAstReltnRepository extends PagingAndSortingRepository<UserAstReltn, Long> {

  String KEYCLOAK_ID = "keyCloakId";

  /**
   * Finds the Assessments mapped to user based on user profile id.
   *
   * @param keyCloakId the profile id
   * @return the List of {@link Assessment}
   */
  @Query(
      value = "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id = :" + KEYCLOAK_ID,
      nativeQuery = true)
  List<UserAstReltn> findByUserId(@Param(KEYCLOAK_ID) String keyCloakId);

  /**
   * Finds the Assessments mapped to user based on user profile id and status filters. Performs
   * Paginated Query.
   *
   * @param keyCloakId the profile id
   * @param filters the list of Assessment Status filters
   * @param pageable the {@link Pageable}
   * @return the List of {@link Assessment}
   */
  @Query(
      value =
          "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id = :"
              + KEYCLOAK_ID
              + " and status in :filters",
      nativeQuery = true)
  List<UserAstReltn> findByUserIdAndFilter(
      @Param(KEYCLOAK_ID) String keyCloakId, @Param("filters") String[] filters, Pageable pageable);

  /**
   * Finds the Assessments mapped to user based on user profile id and status filters.
   *
   * @param keyCloakId the profile id
   * @param filters the list of Assessment Status filters
   * @return the List of {@link Assessment}
   */
  @Query(
      value =
          "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id = :"
              + KEYCLOAK_ID
              + " and status in :filters",
      nativeQuery = true)
  List<UserAstReltn> findByUserIdAndFilter(
      @Param(KEYCLOAK_ID) String keyCloakId, @Param("filters") String[] filters);

  /**
   * Finds the total Assessments mapped to user based on user profile id and status filters.
   *
   * @param keyCloakId the profile id
   * @param filters the list of Assessment Status filters
   * @return the List of {@link Assessment}
   */
  @Query(
      value =
          "SELECT COUNT(*) FROM usr_ast_reltn ua WHERE ua.user_id = :"
              + KEYCLOAK_ID
              + " and status in :filters",
      nativeQuery = true)
  Long countByUserIdAndFilter(
      @Param(KEYCLOAK_ID) String keyCloakId, @Param("filters") String[] filters);

  /**
   * Finds Assessments based on the assessmnet id and user ids.
   *
   * @param keyCloakId the IAM id
   * @param assessmentId the assessment id
   * @return the List of {@link UserAstReltn}
   */
  @Query(
      value =
          "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id in :userId and ua.assessment_id = :assessmentId",
      nativeQuery = true)
  List<UserAstReltn> findAllByUserAstReltn(
      @Param("userId") String[] keyCloakId, @Param("assessmentId") Long assessmentId);

  /**
   * Finds Assessments based on the assessmnet id and keycloak id.
   *
   * @param keyCloakId the IAM id
   * @param assessmentId the assessment id
   * @return the List of {@link UserAstReltn}
   */
  @Query(
      value =
          "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id = :userId and ua.assessment_id = :assessmentId",
      nativeQuery = true)
  UserAstReltn findByUserAstReltn(
      @Param("userId") String keyCloakId, @Param("assessmentId") Long assessmentId);

  /**
   * Finds Assessments based on the related assessment titles and user id.
   *
   * @param keyCloakId the IAM id
   * @param skills the skill names
   * @return the List of {@link UserAstReltn}
   */
  @Query(
      value =
          "SELECT * FROM usr_ast_reltn ua WHERE ua.user_id = :userId and ua.assessment_title in :skills",
      nativeQuery = true)
  List<UserAstReltn> findAllByAssessmentTitle(
      @Param("userId") String keyCloakId, @Param("skills") String[] skills);
}
