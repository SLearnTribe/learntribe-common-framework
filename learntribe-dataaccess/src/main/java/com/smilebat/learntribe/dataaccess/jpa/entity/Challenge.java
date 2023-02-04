package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.smilebat.learntribe.enums.AssessmentDifficulty;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Objects;
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
 * Represents a single Objective Question for user.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Table(name = "CHALLENGE")
@Entity
@Getter
@Setter
@SuppressFBWarnings(justification = "Generated code")
public class Challenge {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String skill;

  @Enumerated(EnumType.STRING)
  private AssessmentDifficulty difficulty;

  private String question;
  private String[] options;
  private String answer;

  @Override
  public int hashCode() {
    return Objects.hash(options);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Challenge other = (Challenge) obj;
    return Objects.equals(this.options, other.options);
  }
}
