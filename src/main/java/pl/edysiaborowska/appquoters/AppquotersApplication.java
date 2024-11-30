package pl.edysiaborowska.appquoters;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;


@SpringBootApplication
public class AppquotersApplication {


	private static final Logger log = (Logger) LoggerFactory.getLogger(AppquotersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppquotersApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			// Metoda GET
			Quote quote = restTemplate.getForObject("http://localhost:8081/api/random", Quote.class);
			log.info("GET: {}", quote);

			// Metoda POST
			QuoteRequest newQuoteRequest = new QuoteRequest("This is a new quote.");
			Quote createdQuote = restTemplate.postForObject("http://localhost:8081/api/quote", newQuoteRequest, Quote.class);
			log.info("POST: {}", createdQuote);

			// Metoda DELETE
			String deleteUrl = "http://localhost:8081/api/quote/3" ;
			restTemplate.delete(deleteUrl);
			log.info("DELETE: Usunięto cytat o ID 2");
		};
	}
}
