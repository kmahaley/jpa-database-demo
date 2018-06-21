package com.learn.spring.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.learn.spring.model.Quote;
import com.learn.spring.repository.QuoteRepository;
import com.learn.spring.service.QuoteService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.stereotype.Service;

/**
 * Quote service implementation
 *
 * @author km185223
 */
//@Slf4j
@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Quote addQuote(Quote quote) throws Exception {
        if (quote.getId() == null) {
            quote.setId(UUID
                    .randomUUID()
                    .toString());
        }
        return quoteRepository.save(quote);
    }

    @Override
    public List<Quote> addAllQuotes(List<Quote> quotes) throws Exception {
        quotes.stream().forEach(quote -> {
            if (quote.getId() == null) {
                quote.setId(UUID
                        .randomUUID()
                        .toString());
            }
        });
        if (CollectionUtils.isNotEmpty(quotes)) {
            return (List<Quote>) quoteRepository.save(quotes);
        }
        return new ArrayList<>();
    }

    @Override
    public Quote updateQuote(String id, Quote quote) throws Exception {
        final Quote savedQuote = getQuoteFromRepository(id);
        quote.setId(savedQuote.getId());
        return quoteRepository.save(quote);
    }

    @Override
    public Quote partialUpdateQuote(String id, Map<String, Object> values) throws Exception {
        final Quote savedQuote = getQuoteFromRepository(id);
        final String name = (String) values.get("name");
        final Object newCostValue = values.get("cost");
        final Integer cost = newCostValue != null ? Integer.valueOf((String) newCostValue) : 0;
        if (values.get("name") != null) {
            savedQuote.setName(name);
        }
        if (newCostValue != null) {
            savedQuote.setCost(cost);
        }
        return quoteRepository.save(savedQuote);
    }

    @Override
    public void deleteQuote(String id) throws Exception {
        if (quoteRepository.exists(id)) {
            quoteRepository.delete(id);
        } else {
            throw new ResourceNotFoundException("quote-id", new DescriptiveResource(id));
        }
    }

    @Override
    public Quote getQuote(String id) throws Exception {
        System.out.println(MDC.get("requestedId"));
        return getQuoteFromRepository(id);
    }

    @Override
    public List<Quote> findAllQuotes() throws Exception {
        List<Integer> list = new ArrayList<>();
        final Iterable<Quote> quotes = quoteRepository.findAll();
        return StreamSupport
                .stream(quotes.spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * @param id quote id
     *
     * @return Quote
     *
     * @throws Exception unhappy case
     */
    private Quote getQuoteFromRepository(String id) throws Exception {
        System.out.println(MDC.get("requestedId"));
        final Quote quote = quoteRepository.findOne(id);
        if (quote == null) {
            throw new ResourceNotFoundException("quote-id", new DescriptiveResource(id));
        }
        return quote;
    }
}
