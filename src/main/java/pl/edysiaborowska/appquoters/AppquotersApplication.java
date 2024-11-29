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
			Quote quote = restTemplate.getForObject(
					"http://localhost:8081/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
}
