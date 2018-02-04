package com.learn.spring.service;

import java.util.Map;

import com.learn.spring.model.Quote;

/**
 * Quote service to
 *
 * @author km185223
 */
public interface QuoteService {

    Quote saveQuote(Quote quote) throws Exception;

    Quote updateQuote(String id, Quote quote) throws Exception;

    Quote partialUpdateQuote(String id, Map<String, Object> values) throws Exception;

    void deleteQuote(String id) throws Exception;

    Quote getQuote(String id) throws Exception;
}
