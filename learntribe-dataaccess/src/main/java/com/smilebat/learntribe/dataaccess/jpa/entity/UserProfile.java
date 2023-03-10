package com.smilebat.learntribe.dataaccess.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.smilebat.learntribe.enums.Gender;
import com.smilebat.learntribe.enums.InterviewStatus;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Set;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

/**
 * User Profile representation of DB.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Sanjay
 * @author Pai,Sai Nandan
 */
@Table(name = "USER_PROFILE")
@Entity
@Indexed
@SuppressFBWarnings(justification = "Generated code")
@SuppressWarnings("PMD.TooManyFields")
@Getter
@Setter
public class UserProfile {

  public static final String USER_DETAILS_NAME = "userProfile";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  private String keyCloakId;

  private String name;
  private String email;

  @FullTextField(analyzer = "wordsearch")
  private String currentDesignation;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @FullTextField(analyzer = "wordsearch")
  private String country;

  private String linkedIn;
  private String gitHub;

  @FullTextField(analyzer = "wordsearch")
  private String skills;

  @Lob private String about;

  private Long phone;

  private Long currentCtc;

  private Long expectedCtc;

  private Long noticePeriod;

  @Enumerated(EnumType.STRING)
  private InterviewStatus availableForInterview;

  @OneToMany(mappedBy = USER_DETAILS_NAME, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {USER_DETAILS_NAME},
      allowSetters = true)
  private Set<WorkExperience> workExperiences = new TreeSet<>(WorkExperience.Comparators.END_DATE);

  @OneToMany(mappedBy = USER_DETAILS_NAME, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {USER_DETAILS_NAME},
      allowSetters = true)
  private Set<EducationExperience> educationExperiences =
      new TreeSet<>(EducationExperience.Comparators.END_DATE);

  @OneToMany(mappedBy = USER_DETAILS_NAME, cascade = CascadeType.ALL)
  @JsonIgnoreProperties(
      value = {USER_DETAILS_NAME},
      allowSetters = true)
  private Set<SideProject> sideProjects = new TreeSet<>(SideProject.Comparators.END_DATE);

  /**
   * Assigns the work experiences for save.
   *
   * @param experiences the Set of {@link WorkExperience}.
   */
  public void setWorkExperiences(Set<WorkExperience> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setUserProfile(this));
      workExperiences = experiences;
    }
  }

  /**
   * Assigns the education experiences for save.
   *
   * @param experiences the Set of {@link EducationExperience}.
   */
  public void setEducationExperiences(Set<EducationExperience> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setUserProfile(this));
      educationExperiences = experiences;
    }
  }

  /**
   * Assigns the side projects for save.
   *
   * @param experiences the Set of {@link SideProject}.
   */
  public void setSideProjects(Set<SideProject> experiences) {
    if (experiences != null) {
      experiences.forEach(experience -> experience.setUserProfile(this));
      sideProjects = experiences;
    }
  }
}
