package pl.edysiaborowska.appquoters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteRequest(String quote) { }