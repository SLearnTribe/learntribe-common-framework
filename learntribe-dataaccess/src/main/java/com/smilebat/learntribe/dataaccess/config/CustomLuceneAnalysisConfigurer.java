package com.smilebat.learntribe.dataaccess.config;

import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

/**
 * Implementation for {@link LuceneAnalysisConfigurer}
 *
 * <p>Copyright &copy; 2023 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
public class CustomLuceneAnalysisConfigurer implements LuceneAnalysisConfigurer {
  public static final String WORD_SEARCH = "wordsearch";

  @Override
  public void configure(LuceneAnalysisConfigurationContext context) {
    context
        .analyzer("english")
        .custom()
        .tokenizer("standard")
        .tokenFilter("lowercase")
        .tokenFilter("snowballPorter")
        .param("language", "English")
        .tokenFilter("asciiFolding");

    context
        .analyzer(WORD_SEARCH)
        .custom()
        .tokenizer("standard")
        .tokenFilter("lowercase")
        .tokenFilter("nGram")
        .param("minGramSize", "3")
        .param("maxGramSize", "4");
  }
}
