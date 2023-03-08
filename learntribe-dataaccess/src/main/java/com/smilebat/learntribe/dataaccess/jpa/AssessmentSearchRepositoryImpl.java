package com.smilebat.learntribe.dataaccess.jpa;

import com.smilebat.learntribe.dataaccess.AssessmentSearchRepository;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserAstReltn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation for {@link AssessmentSearchRepository}
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Service
public class AssessmentSearchRepositoryImpl implements AssessmentSearchRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<UserAstReltn> search(
      String keyword, String[] filters, String keyCloakId, Pageable pageable)
      throws InterruptedException {
    SearchSession searchSession = Search.session(entityManager);
    MassIndexer indexer = searchSession.massIndexer(UserAstReltn.class).threadsToLoadObjects(5);
    indexer.startAndWait();

    return searchSession
        .search(UserAstReltn.class)
        .where(
            f ->
                f.bool()
                    .must(f.bool().should(f.match().fields("assessmentTitle").matching(keyword)))
                    .must(f.match().field("userId").matching(keyCloakId)))
        .fetchHits(pageable.getPageNumber(), pageable.getPageSize());
  }
}
