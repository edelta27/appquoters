package pl.edysiaborowska.appquoters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) {
    // Dodatkowy konstruktor dla uproszczonej wersji (tylko cytat)
    public Quote(String quote) {
        this("success", new Value(null, quote));
    }
}
