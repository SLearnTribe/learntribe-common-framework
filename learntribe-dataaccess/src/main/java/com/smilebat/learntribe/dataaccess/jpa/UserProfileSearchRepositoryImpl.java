package com.smilebat.learntribe.dataaccess.jpa;

import com.smilebat.learntribe.dataaccess.UserProfileSearchRepository;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserProfile;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation for {@link UserProfileSearchRepository}
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
@Service
public class UserProfileSearchRepositoryImpl implements UserProfileSearchRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<UserProfile> search(String keyword, Pageable pageable) throws InterruptedException {
    SearchSession searchSession = Search.session(entityManager);
    MassIndexer indexer = searchSession.massIndexer(UserProfile.class).threadsToLoadObjects(5);
    indexer.startAndWait();

    return searchSession
        .search(UserProfile.class)
        .where(
            f ->
                f.bool()
                    .should(
                        f.match()
                            .fields("skills", "country", "currentDesignation")
                            .matching(keyword)))
        .fetchHits(pageable.getPageNumber(), pageable.getPageSize());
  }
}
