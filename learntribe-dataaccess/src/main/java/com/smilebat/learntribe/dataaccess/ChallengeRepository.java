package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.Challenge;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Assessment Repo */
@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
  /**
   * Finds the challenges based on skill.
   *
   * @param skill the {@link String} skill.
   * @param limit the limit of records.
   * @param offset the offset of records.
   * @return the List of {@link Challenge}.
   */
  @Query(
      value =
          "SELECT * FROM CHALLENGE c WHERE c.skill = :skill ORDER BY id LIMIT :limit OFFSET :offset",
      nativeQuery = true)
  Set<Challenge> findBySkill(
      @Param("skill") String skill, @Param("limit") int limit, @Param("offset") int offset);
}
