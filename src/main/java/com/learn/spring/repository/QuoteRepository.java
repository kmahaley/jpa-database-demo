package com.learn.spring.repository;

import com.learn.spring.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repository to save quote
 *
 * @author km185223
 */
@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
}
