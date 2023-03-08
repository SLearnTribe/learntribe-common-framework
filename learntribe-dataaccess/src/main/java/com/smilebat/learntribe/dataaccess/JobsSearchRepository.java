package com.smilebat.learntribe.dataaccess;

import com.smilebat.learntribe.dataaccess.jpa.entity.UserObReltn;
import java.util.List;
import org.springframework.data.domain.Pageable;

/** Returns Data Access by Job Repo */
public interface JobsSearchRepository {

  /**
   * Searches for keyword using lucene query in db.
   *
   * @param keyword the search word
   * @param keyCloakId the IAM user id
   * @param pageable the {@link Pageable}.
   * @return the List of {@link UserObReltn}
   * @throws InterruptedException on db error
   */
  List<UserObReltn> search(String keyword, String keyCloakId, Pageable pageable)
      throws InterruptedException;
}
