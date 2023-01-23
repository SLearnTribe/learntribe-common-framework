package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.time.Instant;
import java.util.Comparator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Work Experience representation of DB.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Sanjay
 */
@Getter
@Setter
@Table(name = "RESUME_SIDE_PROJECT")
@Entity
@SuppressFBWarnings(justification = "Generated code")
public class ResumeSideProject implements Experience, Comparable<ResumeSideProject> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String name;

  @Lob private String url;
  @Lob private String skills;

  protected Instant startDate;
  protected Instant endDate;

  @Lob private String description;

  @ManyToOne(optional = false)
  @NotNull
  @JsonIgnoreProperties(
      value = {"educationExperiences", "workExperiences", "sideProjects"},
      allowSetters = true)
  protected Resume resume;

  @Override
  public int compareTo(ResumeSideProject o) {
    if (o != null && endDate != null) {
      return o.endDate.compareTo(endDate);
    }
    return 0;
  }

  /** Wrapper class for comparator sorting based on date. */
  public static class Comparators {
    public static final Comparator<ResumeSideProject> END_DATE =
        Comparator.comparing(ResumeSideProject::getEndDate);
  }
}
