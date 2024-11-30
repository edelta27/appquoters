package pl.edysiaborowska.appquoters.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edysiaborowska.appquoters.QuoteResult;
import pl.edysiaborowska.appquoters.QuoteResponse;
@Component
public class SampleQuoteServerProxy {
    @Autowired
    RestTemplate restTemplate;

    @Value("${localhost}")
    String url;

    @Value("${8081}")
    int port;

    public String makePostRequest() {
        UriComponentsBuilder builder = UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(url)
                .port(port)
                .path("/api/quote");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("requestId", "dodatkowy wpis");
        QuoteResponse dodanyValue = new QuoteResponse(15L,"String działa świetnie" );
        QuoteResult requestBody = new QuoteResult("success",dodanyValue);
        HttpEntity<QuoteResult> httpEntity = new HttpEntity<>(requestBody,httpHeaders);

        try{
            ResponseEntity<String> response = restTemplate.exchange(
                    builder.build().toUri(),
                    HttpMethod.POST,
                    httpEntity,
                    String.class
            );
            return response.getBody();
        }catch (RestClientResponseException exception){
            System.out.println(exception.getStatusText() + " " + exception.getStatusCode().value() );
        }catch (RestClientException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
