package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;

/**
 * Defines the Resume table.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Table(name = "RESUME")
@Entity
@Indexed
@Getter
@Setter
@SuppressFBWarnings(justification = "Generated code")
public class Resume {

  public static final String RESUME_DETAIL = "resume";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String keyCloakId;

  private String name;
  private String email;
  private String currentDesignation;

  @Field(termVector = TermVector.YES)
  private String country;

  private String city;

  private String linkedIn;

  @Field(
      termVector = TermVector.YES,
      store = Store.NO,
      analyzer = @Analyzer(definition = "textanalyzer"))
  private String skills;

  @Field(termVector = TermVector.YES)
  @Lob
  private String about;

  @Lob private String address;

  private Long phone;

  @OneToMany(mappedBy = RESUME_DETAIL, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {RESUME_DETAIL},
      allowSetters = true)
  private Set<ResumeWorkExperience> workExperiences =
      new TreeSet<>(ResumeWorkExperience.Comparators.END_DATE);

  @OneToMany(mappedBy = RESUME_DETAIL, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {RESUME_DETAIL},
      allowSetters = true)
  private Set<ResumeEducationExperience> educationExperiences =
      new TreeSet<>(ResumeEducationExperience.Comparators.END_DATE);

  @OneToMany(mappedBy = RESUME_DETAIL, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {RESUME_DETAIL},
      allowSetters = true)
  private Set<ResumeSideProject> sideProjects =
      new TreeSet<>(ResumeSideProject.Comparators.END_DATE);

  /**
   * Assigns the work experiences for save.
   *
   * @param experiences the Set of {@link ResumeWorkExperience}.
   */
  public void setWorkExperiences(Set<ResumeWorkExperience> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setResume(this));
      workExperiences = experiences;
    }
  }

  /**
   * Assigns the education experiences for save.
   *
   * @param experiences the Set of {@link EducationExperience}.
   */
  public void setEducationExperiences(Set<ResumeEducationExperience> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setResume(this));
      educationExperiences = experiences;
    }
  }

  /**
   * Assigns the side projects for save.
   *
   * @param experiences the Set of {@link ResumeSideProject}.
   */
  public void setSideProjects(Set<ResumeSideProject> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setResume(this));
      sideProjects = experiences;
    }
  }
}
