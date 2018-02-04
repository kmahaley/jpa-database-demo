package com.learn.spring.service.impl;

import java.util.Map;

import com.learn.spring.model.Quote;
import com.learn.spring.repository.QuoteRepository;
import com.learn.spring.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.stereotype.Service;

/**
 * Quote service implementation
 *
 * @author km185223
 */
@Service
public class QuoteServiceImpl implements QuoteService {

    private QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public Quote saveQuote(Quote quote) throws Exception {
        return quoteRepository.save(quote);
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
        return getQuoteFromRepository(id);
    }

    /**
     * @param id quote id
     *
     * @return Quote
     *
     * @throws Exception unhappy case
     */
    private Quote getQuoteFromRepository(String id) throws Exception {
        final Quote quote = quoteRepository.findOne(id);
        if (quote == null) {
            throw new ResourceNotFoundException("quote-id", new DescriptiveResource(id));
        }
        return quote;
    }
}
