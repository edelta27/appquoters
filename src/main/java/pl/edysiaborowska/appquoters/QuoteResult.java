package pl.edysiaborowska.appquoters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteResult(String type, QuoteResponse quoteResponse) { }
