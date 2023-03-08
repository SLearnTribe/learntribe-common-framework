package com.smilebat.learntribe.dataaccess.jpa;

import com.smilebat.learntribe.dataaccess.JobsSearchRepository;
import com.smilebat.learntribe.dataaccess.jpa.entity.UserObReltn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.DatabaseRetrievalMethod;
import org.hibernate.search.query.ObjectLookupMethod;
import org.hibernate.search.query.dsl.MustJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
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
  @PersistenceContext private EntityManager em;

  @Override
  public List<UserObReltn> search(String keyword, String keyCloakId, Pageable pageable)
      throws InterruptedException {
    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
    fullTextEntityManager.createIndexer().startAndWait();
    QueryBuilder queryBuilder =
        fullTextEntityManager
            .getSearchFactory()
            .buildQueryBuilder()
            .forEntity(UserObReltn.class)
            .get();

    Query userIdQuery = queryBuilder.keyword().onField("userId").matching(keyCloakId).createQuery();

    Query locationQuery =
        queryBuilder
            .keyword()
            .onFields("location", "title", "businessName", "requiredSkills")
            .matching(keyword)
            .createQuery();

    MustJunction intermediateCriteria = queryBuilder.bool().must(locationQuery).must(userIdQuery);

    Query finalQuery = intermediateCriteria.createQuery();

    // wrap Lucene query in a javax.persistence.Query
    FullTextQuery jpaQuery =
        fullTextEntityManager.createFullTextQuery(finalQuery, UserObReltn.class);
    jpaQuery.initializeObjectsWith(
        ObjectLookupMethod.SECOND_LEVEL_CACHE, DatabaseRetrievalMethod.QUERY);
    jpaQuery.setMaxResults(pageable.getPageSize());
    jpaQuery.setFirstResult(pageable.getPageNumber());

    return jpaQuery.getResultList();
  }
}
