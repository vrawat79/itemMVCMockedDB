package com.performance.itemMVC.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.client.RestTemplate;

import com.performance.itemMVC.domain.Item;

@Component
public class ReactiveItemRepository {

	@Value("${mockedDBServicehost}")
	String mockedDbServiceHost;

	String url;
//	WebClient webClient;  
//	
//	public ReactiveItemRepository() {
//		// TODO Auto-generated constructor stub
//		this.url = "http://" + mockedDbServiceHost + ":8080/items/Laptop";
//		this.webClient = WebClient.builder().baseUrl(url).build();       
//	}
//	
//	@PostConstruct
//    private void postConstruct() {
//		System.out.println("url: " + url);
//		this.url = "http://" + mockedDbServiceHost + ":8080/items/Laptop";
//		this.webClient = WebClient.builder().baseUrl(url).build();
//		System.out.println("url: " + url);
//    }

	RestTemplate restTemplate;

	public ReactiveItemRepository() {
		// TODO Auto-generated constructor stub
		this.url = "http://" + mockedDbServiceHost + ":8080/items/Laptop";
//		this.restTemplate = WebClient.builder().baseUrl(url).build();
		this.restTemplate = new RestTemplate();
	}

	@PostConstruct
	private void postConstruct() {
		System.out.println("url: " + url);
		this.url = "http://" + mockedDbServiceHost + ":8080/items/Laptop";
//		this.webClient = WebClient.builder().baseUrl(url).build();
		System.out.println("url: " + url);
	}

	public List<Item> findByCategory(String category) {

		ResponseEntity<List<Item>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Item>>() {
				});
		List<Item> itemList = response.getBody();

		return itemList;

//		return webClient.get().uri("/").exchange().flatMapMany(response -> response.bodyToFlux(Item.class));
	}

//	public Flux<Item> saveAll(Flux<Item> items) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public Mono<Item> save(Item item) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
