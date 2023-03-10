package com.smilebat.learntribe.dataaccess.jpa.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a relation between Assessment and Challenges. Many to Many
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Getter
@Setter
@Table(name = "AST_CHLNG_RELTN")
@Entity
@SuppressFBWarnings(justification = "Generated code")
public class AstChallengeReltn {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private Long assessmentId;
  private Long challengeId;
}
