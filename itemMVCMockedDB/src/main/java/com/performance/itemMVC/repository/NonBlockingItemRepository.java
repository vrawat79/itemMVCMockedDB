package com.performance.itemMVC.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

	Map<String, List<Item>> resultMap = new HashMap<String, List<Item>>();

	@Override
	public Flux<Item> findByCategory(String category) {
		Flux<Item> itemFlux = null;
		if (category.equalsIgnoreCase("catalogue")) {
			return itemFlux = webClient.get().uri("/stream/" + category).exchange()
					.flatMapMany(response -> response.bodyToFlux(Item.class));
		}

		itemFlux = webClient.get().uri("/" + category).exchange()
				.flatMapMany(response -> response.bodyToFlux(Item.class));

		String txid = UUID.randomUUID().toString();
		resultMap.put(txid, new ArrayList<Item>());

		// blocking implementation
		itemFlux.collectList().map(itemList -> {
			System.out.println("---- save to db: " + itemList);
			return itemList;
		}).subscribe();

		// non-blocking implementation
		itemFlux.map(item -> {
			List<Item> itemList = resultMap.get(txid);
			itemList.add(item);
			return item;
		}).doOnComplete(() -> {
			System.out.println("-- publish an event with the list to an async consumer ---");
			List<Item> itemList = resultMap.get(txid);

			// save the itemList to DB or publish to bus for further logic

			// remove the txid from the map
			resultMap.remove(txid);
		}).subscribe();

		return itemFlux;
	}

}
