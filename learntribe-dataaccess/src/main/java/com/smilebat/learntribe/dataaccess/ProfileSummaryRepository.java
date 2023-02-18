package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.ProfileSummary;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by User Profile Summary Repository */
@Repository
public interface ProfileSummaryRepository extends PagingAndSortingRepository<ProfileSummary, Long> {
  /**
   * Queries summaries based on user roles.
   *
   * @param role the role of the user
   * @param pageable the {@link Pageable}
   * @return summaries {@link ProfileSummary}.
   */
  @Query(value = "SELECT * FROM PROFILE_SUMMARY p WHERE p.role = :role", nativeQuery = true)
  List<ProfileSummary> findByRole(@Param("role") String role, Pageable pageable);

  /**
   * Queries summaries based on user roles and skills.
   *
   * @param role the role of the user.
   * @param skill the skill of the user.
   * @param pageable the {@link Pageable}
   * @return summaries {@link ProfileSummary}.
   */
  @Query(
      value = "SELECT * FROM PROFILE_SUMMARY p WHERE p.role = :role and p.skill = :skill",
      nativeQuery = true)
  List<ProfileSummary> findByRoleAndSkill(
      @Param("role") String role, @Param("skill") String skill, Pageable pageable);
}
