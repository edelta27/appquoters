package pl.edysiaborowska.appquoters.proxy;

import java.util.List;
public record SampleServerQuoteResponse (
    String message,
    List<String> api
){
}
