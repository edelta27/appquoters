package pl.edysiaborowska.appquoters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import pl.edysiaborowska.appquoters.proxy.SampleQuoteServerProxy;
import pl.edysiaborowska.appquoters.proxy.SampleServerQuoteResponse;


@SpringBootApplication
public class AppquotersApplication {
	@Autowired
	SampleQuoteServerProxy sampleQuoteServerProxy;
	public static void main(String[] args) {
		SpringApplication.run(AppquotersApplication.class, args);
	}
//	@Bean
//	@Profile("!test")
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//			Quote quote = restTemplate.getForObject(
//					"http://localhost:8081//apiWithRequestParam", Quote.class);
//			log.info(quote.toString());
//		};
//	}
	@EventListener(ApplicationStartedEvent.class)
	public void run() throws JsonProcessingException {
		String postJsonSampleQuoteServer = sampleQuoteServerProxy.makePostRequest();
		if (postJsonSampleQuoteServer != null) {
			SampleServerQuoteResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(postJsonSampleQuoteServer);
			System.out.println(sampleShawnMendesResponse);
		}
//		String getJsonSampleQuoteServer = sampleQuoteProxy.makeGetRequest();
//
//
//		if (getJsonSampleShawnMendesServer != null) {
//			SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer);
//			System.out.println(sampleShawnMendesResponse);
//		}
	}

//	@Bean
//	@Profile("!test2")
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		Valued nowyV = new Valued(Long 1, String "nowyQ");
//		QuoteResult nowyQ = new QuoteResult("type", nowyV);
//		return args -> {
//			QuoteResult quote = restTemplate.postForObject(
//					"http://localhost:8081/api/quote", nowyQ , QuoteResult.class);
//			log.info(quote.toString());
//		};
//	}
	private QuoteResponse mapJsonToQuoteResponse(String json) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, QuoteResponse.class);
	}

	private SampleServerQuoteResponse mapJsonToSampleShawnMendesResponse(String json) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, SampleServerQuoteResponse.class);
	}
}
