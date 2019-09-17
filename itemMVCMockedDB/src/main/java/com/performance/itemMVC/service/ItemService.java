package com.performance.itemMVC.service;

import java.util.List;

import com.performance.itemMVC.domain.Item;

import reactor.core.publisher.Flux;



public interface ItemService {
	
	public Flux<Item> findByCategory(String category);
//	public Mono<Item> createItem(Item item);	
//	public Flux<Item> createAllItems(Flux<Item> items);

}
