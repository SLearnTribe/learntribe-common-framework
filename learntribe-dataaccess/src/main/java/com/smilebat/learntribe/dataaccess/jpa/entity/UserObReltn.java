package com.smilebat.learntribe.dataaccess.jpa.entity;

import static com.smilebat.learntribe.dataaccess.config.CustomLuceneAnalysisConfigurer.WORD_SEARCH;

import com.smilebat.learntribe.enums.HiringStatus;
import com.smilebat.learntribe.enums.UserObReltnType;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

/**
 * Defines the relationship between User and Job Entity in DB.
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Getter
@Setter
@Table(name = "USR_OB_RELTN")
@Entity
@Indexed
@SuppressFBWarnings(justification = "Generated code")
public class UserObReltn {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @KeywordField private String userId;

  @Enumerated(EnumType.STRING)
  @GenericField
  private HiringStatus hiringStatus;

  @Enumerated(EnumType.STRING)
  private UserObReltnType userObReltn;

  @Lob
  @FullTextField(analyzer = WORD_SEARCH)
  private String requiredSkills;

  @Lob
  @FullTextField(analyzer = WORD_SEARCH)
  private String title;

  @FullTextField(analyzer = WORD_SEARCH)
  private String businessName;

  @FullTextField(analyzer = WORD_SEARCH)
  private String location;

  private Long jobId;
}
