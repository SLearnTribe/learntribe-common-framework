package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.WorkQueue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Returns Data Access by WorkQueue Repo */
@Repository
public interface WorkQueueRepository extends JpaRepository<WorkQueue, Long> {

  /**
   * Queries WorkQueue based on Candidate ID & hr ID.
   *
   * @param userId the IAM id
   * @param hrId the IAM HR id
   * @return assessment.
   */
  @Query(
      value = "SELECT * FROM WORK_QUEUE q WHERE q.created_for = :userId and q.created_by = :hrId",
      nativeQuery = true)
  WorkQueue findByHrCreated(@Param("userId") String userId, @Param("hrId") String hrId);

  /**
   * Queries WorkQueue based on Candidate ID & System generated.
   *
   * @param userId the IAM id
   * @return assessment.
   */
  @Query(
      value =
          "SELECT * FROM WORK_QUEUE q WHERE q.created_for = :userId and q.created_by = 'SYSTEM'",
      nativeQuery = true)
  WorkQueue findBySystemCreated(@Param("userId") String userId);

  /**
   * Queries WorkQueue based on Candidate ID.
   *
   * @param userId the IAM id
   * @return assessment.
   */
  @Query(value = "SELECT * FROM WORK_QUEUE q WHERE q.created_for = :userId", nativeQuery = true)
  List<WorkQueue> findByUserId(@Param("userId") String userId);
}
