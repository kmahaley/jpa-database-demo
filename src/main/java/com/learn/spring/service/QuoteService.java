package com.learn.spring.service;

import java.util.List;
import java.util.Map;

import com.learn.spring.model.Quote;

/**
 * Quote service to
 *
 * @author km185223
 */
public interface QuoteService {

    Quote addQuote(Quote quote) throws Exception;

    List<Quote> addAllQuotes(List<Quote> quotes) throws Exception;

    Quote updateQuote(String id, Quote quote) throws Exception;

    Quote partialUpdateQuote(String id, Map<String, Object> values) throws Exception;

    void deleteQuote(String id) throws Exception;

    Quote getQuote(String id) throws Exception;

    List<Quote> findAllQuotes() throws Exception;

    String readList() throws Exception;
}
