package com.smilebat.learntribe.dataaccess.jpa.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Defines the Profile Summary Table.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Table(name = "PROFILE_SUMMARY")
@Entity
@Getter
@Setter
@SuppressFBWarnings(justification = "Generated code")
public class ProfileSummary {
  public static final String PROFILE_SUMMARY = "profileSummary";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String skill;
  private String role;
  @Lob private String summary;
}
