package com.smilebat.learntribe.dataaccess.jpa;

import com.smilebat.learntribe.dataaccess.JobsSearchRepository;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserObReltn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation for {@link JobsSearchRepository}
 *
 * <p>Copyright &copy; 2023 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Service
public class JobSearchRepositoryImpl implements JobsSearchRepository {
  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<UserObReltn> search(String keyword, String keyCloakId, Pageable pageable)
      throws InterruptedException {
    SearchSession searchSession = Search.session(entityManager);
    MassIndexer indexer = searchSession.massIndexer(UserObReltn.class).threadsToLoadObjects(5);
    indexer.startAndWait();

    return searchSession
        .search(UserObReltn.class)
        .where(
            f ->
                f.bool()
                    .must(
                        f.bool()
                            .should(
                                f.match()
                                    .fields("title", "location", "businessName", "requiredSkills")
                                    .matching(keyword)))
                    .must(f.match().field("userId").matching(keyCloakId)))
        .fetchHits(pageable.getPageNumber(), pageable.getPageSize());
  }
}
