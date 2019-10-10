package com.performance.itemMVC.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.performance.itemMVC.domain.Item;

import reactor.core.publisher.Flux;

@Component
@Profile("nonBlocking")
public class NonBlockingItemRepository implements ItemRepository {

	@Value("${mockedDBServicehost}")
	String mockedDbServiceHost;

	String url;
	WebClient webClient;

	@PostConstruct
	private void postConstruct() {
		System.out.println("--- NonBlocking Client ---");
//		System.out.println("url: " + url);
		this.url = "http://" + mockedDbServiceHost + ":8080/items";
		this.webClient = WebClient.builder().baseUrl(url).build();
		System.out.println("url: " + url);
	}

	@Override
	public Flux<Item> findByCategory(String category) {		
		Flux<Item> itemFlux = null;
		if (category.equalsIgnoreCase("catalogue")) {
			return itemFlux = webClient.get().uri("/stream/" + category).exchange()
					.flatMapMany(response -> response.bodyToFlux(Item.class));
		}

		itemFlux = webClient.get().uri("/" + category).exchange()
				.flatMapMany(response -> response.bodyToFlux(Item.class));
		return itemFlux;
	}

//	public List<Item> findByCategory(String category) {
//
//		ResponseEntity<List<Item>> response = restTemplate.exchange(url, HttpMethod.GET, null,
//				new ParameterizedTypeReference<List<Item>>() {
//				});
//		List<Item> itemList = response.getBody();
//
//		return itemList;
//
////		return webClient.get().uri("/").exchange().flatMapMany(response -> response.bodyToFlux(Item.class));
//	}

}
