package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.smilebat.learntribe.enums.AssessmentDifficulty;
import com.smilebat.learntribe.enums.QueueStatus;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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

/**
 * User Profile representation of DB.
 *
 * <p>Copyright &copy; 2023 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Getter
@Setter
@Table(name = "WORK_QUEUE")
@Entity
@SuppressFBWarnings(justification = "Generated code")
public class WorkQueue {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String skills;

  private String createdFor;

  private String createdBy;

  @Enumerated(EnumType.STRING)
  private QueueStatus status;

  @Enumerated(EnumType.STRING)
  private AssessmentDifficulty difficulty;

  private Long relatedJobId;
}
