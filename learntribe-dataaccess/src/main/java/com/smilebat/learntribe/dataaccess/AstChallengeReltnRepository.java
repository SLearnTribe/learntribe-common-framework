package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.AstChallengeReltn;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Assessment Repo */
@Repository
public interface AstChallengeReltnRepository extends JpaRepository<AstChallengeReltn, Long> {
  /**
   * Finds the challenges based on skill.
   *
   * @param id the {@link Long} Assessment Id.
   * @return the List of {@link AstChallengeReltn}.
   */
  @Query(value = "SELECT * FROM AST_CHLNG_RELTN c WHERE c.assessment_id = :id", nativeQuery = true)
  Set<AstChallengeReltn> findByAssessmentId(@Param("id") Long id);
}
