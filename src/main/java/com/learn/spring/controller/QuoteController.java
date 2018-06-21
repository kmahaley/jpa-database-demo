package com.learn.spring.controller;

import java.util.List;
import java.util.Map;

import com.learn.spring.model.Quote;
import com.learn.spring.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Quotes controller
 *
 * @author km185223
 */
//@Slf4j
@RestController
@RequestMapping("/quotes")
@Api(value = "Quote resource", description = "Operations pertaining to quote resource")
public class QuoteController {

    private QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @ApiOperation(value = "Get quote by id", response = Quote.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved quote"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/{id}")
    public @ResponseBody Quote get(@PathVariable("id") final String id) throws Exception {
        MDC.put("requestedId",id+"  _requestedByUser");
        return quoteService.getQuote(id);
    }

    @ApiOperation(value = "Create single quote", response = Quote.class)
    @PostMapping()
    public @ResponseBody Quote add(@RequestBody Quote quote) throws Exception {
        return quoteService.addQuote(quote);
    }

    @ApiOperation(value = "Create multiple quotes", response = List.class)
    @PostMapping("/add-all")
    public @ResponseBody List<Quote> addAll(@RequestBody List<Quote> quotes) throws Exception {
        return quoteService.addAllQuotes(quotes);
    }

    @ApiOperation(value = "Update quote by id", response = Quote.class)
    @PutMapping("/{id}")
    public @ResponseBody Quote put(@PathVariable("id") final String id, @RequestBody final Quote quote)
            throws Exception {
        return quoteService.updateQuote(id, quote);
    }

    @ApiOperation(value = "Delete quote by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) throws Exception {
        quoteService.deleteQuote(id);
    }

    @ApiOperation(value = "Patch quote by id", response = Quote.class)
    @PatchMapping("/{id}")
    public @ResponseBody Quote patch(@PathVariable("id") String id, @RequestBody Map<String, Object> body)
            throws Exception {
        return quoteService.partialUpdateQuote(id, body);
    }

    @ApiOperation(value = "Find all quotes", response = List.class)
    @PostMapping("/find-all")
    public @ResponseBody List<Quote> findAll() throws Exception {
        return quoteService.findAllQuotes();
    }
}
