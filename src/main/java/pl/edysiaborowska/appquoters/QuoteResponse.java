package pl.edysiaborowska.appquoters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public record QuoteResponse(Long id, String quote) { }

