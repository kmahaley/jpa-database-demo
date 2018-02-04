package com.learn.spring.repository;

import com.learn.spring.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository to save quote
 *
 * @author km185223
 */
@Repository
public interface QuoteRepository extends CrudRepository<Quote, String> {
}
