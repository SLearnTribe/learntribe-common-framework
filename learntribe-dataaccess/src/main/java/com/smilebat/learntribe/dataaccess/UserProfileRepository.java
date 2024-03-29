package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.UserProfile;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by User Repo */
@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile, Long> {

  /**
   * Finds the profile based on IAM user id.
   *
   * @param keycloakId the IAM user id.
   * @return the {@link UserProfile}
   */
  @Query(
      value = "SELECT * FROM USER_PROFILE a WHERE a.key_cloak_id = :keyCloakId",
      nativeQuery = true)
  UserProfile findByKeyCloakId(@Param("keyCloakId") String keycloakId);

  /**
   * Finds the profile based on user email ids.
   *
   * @param email the IAM user emails.
   * @return the {@link UserProfile}
   */
  @Query(value = "SELECT * FROM USER_PROFILE a WHERE a.email like :email", nativeQuery = true)
  UserProfile findByEmail(@Param("email") String email);

  /**
   * Finds the profile based on user email ids.
   *
   * @param emails the IAM user emails.
   * @return the {@link UserProfile}
   */
  @Query(value = "SELECT * FROM USER_PROFILE a WHERE a.email in :emails", nativeQuery = true)
  List<UserProfile> findAllByEmail(@Param("emails") String[] emails);

  /**
   * Finds the profile based on user email ids.
   *
   * @param email the IAM user emails.
   * @return the {@link UserProfile}
   */
  @Query(value = "SELECT * FROM USER_PROFILE a WHERE a.email in :email", nativeQuery = true)
  List<UserProfile> listByEmail(@Param("email") String email);

  /**
   * Finds all valid user profiles
   *
   * @param pageable pageable object for pagination
   * @param keyword for searching users
   * @param skill for searching users based on skill
   * @return the List of {@link UserProfile}
   */
  @Query(
      value =
          "SELECT * FROM USER_PROFILE WHERE ( KEY_CLOAK_ID is not null AND "
              + "skills ~* :skill AND "
              + "skills ~* :keyword OR "
              + "country ~* :keyword OR "
              + "about ~* :keyword)",
      nativeQuery = true)
  List<UserProfile> findAllUsers(
      Pageable pageable, @Param("skill") String skill, @Param("keyword") String keyword);

  /**
   * Finds the profile based on skill.
   *
   * @param pageable object for pageination.
   * @param skill skill needed in the candidate.
   * @return the List of {@link UserProfile}
   */
  @Query(value = "FROM UserProfile A WHERE A.skills like %:skill%", nativeQuery = false)
  List<UserProfile> findBySkills(@Param("skill") String skill, Pageable pageable);
}
