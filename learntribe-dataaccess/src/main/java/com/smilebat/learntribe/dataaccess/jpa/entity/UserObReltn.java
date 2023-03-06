package com.smilebat.learntribe.dataaccess.jpa.entity;

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
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TermVector;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

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
@AnalyzerDef(
    name = UserObReltn.ANALYZER_NAME,
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
      @TokenFilterDef(factory = LowerCaseFilterFactory.class),
      @TokenFilterDef(
          factory = SnowballPorterFilterFactory.class,
          params = {@Parameter(name = "language", value = "English")})
    })
public class UserObReltn {
  public static final String ANALYZER_NAME = "textanalyzer1";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Long id;

  @Field(termVector = TermVector.YES)
  private String userId;

  @Enumerated(EnumType.STRING)
  private HiringStatus hiringStatus;

  @Enumerated(EnumType.STRING)
  private UserObReltnType userObReltn;

  @Field(
      termVector = TermVector.YES,
      store = Store.NO,
      analyzer = @Analyzer(definition = ANALYZER_NAME))
  @Lob
  private String requiredSkills;

  @Field(
      termVector = TermVector.YES,
      store = Store.NO,
      analyzer = @Analyzer(definition = ANALYZER_NAME))
  @Lob
  private String title;

  @Field(
      termVector = TermVector.YES,
      store = Store.NO,
      analyzer = @Analyzer(definition = ANALYZER_NAME))
  private String businessName;

  @Field(
      termVector = TermVector.YES,
      store = Store.NO,
      analyzer = @Analyzer(definition = ANALYZER_NAME))
  private String location;

  private Long jobId;
}
