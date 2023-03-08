package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.smilebat.learntribe.enums.AssessmentStatus;
import com.smilebat.learntribe.enums.UserAstReltnType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.function.BiFunction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

/**
 * Defines the relationship between User and Assessment Entity in DB.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Getter
@Setter
@Table(name = "USR_AST_RELTN")
@Entity
@Indexed
@SuppressFBWarnings(justification = "Generated code")
public class UserAstReltn {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String userId;

  private Long assessmentId;

  @FullTextField(analyzer = "wordsearch")
  private String assessmentTitle;

  private Integer questions;

  private Integer answered;

  @Enumerated(EnumType.STRING)
  private AssessmentStatus status;

  @Enumerated(EnumType.STRING)
  private UserAstReltnType userAstReltnType;

  /**
   * Bi function for apply appropriate method
   *
   * @param userId the IAM id
   * @param assessment the mapping assessment
   * @param function the functional Interface
   * @return the {@link UserAstReltn}
   */
  public static UserAstReltn create(
      String userId, Assessment assessment, BiFunction<String, Assessment, UserAstReltn> function) {
    return function.apply(userId, assessment);
  }

  /**
   * Creates a User Assessment relation object for Candidate.
   *
   * @param userId the keyCloak user Id
   * @param assessment the Assessment to be assigned
   * @return the {@link UserAstReltn}
   */
  public static UserAstReltn applyReltnForCandidate(String userId, Assessment assessment) {
    UserAstReltn userAstReltn = new UserAstReltn();
    userAstReltn.setUserId(userId);
    userAstReltn.setAssessmentId(assessment.getId());
    userAstReltn.setAssessmentTitle(assessment.getTitle());
    userAstReltn.setStatus(AssessmentStatus.PENDING);
    userAstReltn.setUserAstReltnType(UserAstReltnType.ASSIGNED);
    userAstReltn.setQuestions((int) assessment.getQuestions());
    userAstReltn.setAnswered(0);
    return userAstReltn;
  }

  /**
   * Creates a User Assessment relation object for HR.
   *
   * @param userId the keyCloak user Id
   * @param assessment the Assessment to be assigned
   * @return the {@link UserAstReltn}
   */
  public static UserAstReltn applyReltnForHr(String userId, Assessment assessment) {
    UserAstReltn userAstReltn = new UserAstReltn();
    userAstReltn.setUserId(userId);
    userAstReltn.setAssessmentId(assessment.getId());
    userAstReltn.setAssessmentTitle(assessment.getTitle());
    userAstReltn.setStatus(AssessmentStatus.DEFAULT);
    userAstReltn.setUserAstReltnType(UserAstReltnType.CREATED);
    userAstReltn.setQuestions(0);
    userAstReltn.setAnswered(0);
    return userAstReltn;
  }
}
