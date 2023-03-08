package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.Assessment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by Assessment Repo */
@Repository
public interface AssessmentRepository extends PagingAndSortingRepository<Assessment, Long> {

  /**
   * Queries assessment ids based on related jobs.
   *
   * @param jobIds the list of jobs
   * @return the Optional of {@link Assessment}.
   */
  @Query(
      value =
          "SELECT ast.id FROM assessment ast inner join others_business ob on ast.related_job_id = ob.id WHERE ob.id in :jobIds",
      nativeQuery = true)
  List<Long> findByRelatedJobs(@Param("jobIds") Long[] jobIds);

  /**
   * Queries assessment ids based on titles and user id.
   *
   * @param userId the IAM userId
   * @param titles the list of title
   * @return the List of {@link Long} ids.
   */
  @Query(
      value = "SELECT a.id FROM assessment a WHERE a.created_by = :userId and a.title in :titles",
      nativeQuery = true)
  List<Long> findByUserAndTitles(
      @Param("userId") String userId, @Param("titles") List<String> titles);

  /**
   * Queries assessments based on title,difficulty and user id.
   *
   * @param userId the IAM userId
   * @param title the title
   * @param difficulty the difficulty
   * @return the Optional of {@link Assessment}.
   */
  @Query(
      value =
          "SELECT * FROM assessment a WHERE a.title like :title and a.created_by = :userId and a.difficulty = :difficulty",
      nativeQuery = true)
  Optional<Assessment> findByUserTitleDifficulty(
      @Param("userId") String userId,
      @Param("title") String title,
      @Param("difficulty") String difficulty);

  /**
   * Queries assessments based on Assessment ID.
   *
   * @param id Assessment ID
   * @return assessment.
   */
  @Query(value = "SELECT * FROM assessment a WHERE a.id = :id", nativeQuery = true)
  Assessment findByAssessmentId(@Param("id") Long id);

  /**
   * Queries assessments based on title and user id.
   *
   * @param assessmentIds array of Assessment IDs
   * @return the List of {@link Assessment}.
   */
  @Query(value = "SELECT * FROM assessment where id in :ids", nativeQuery = true)
  List<Assessment> findAllByIds(@Param("ids") Long[] assessmentIds);
}
