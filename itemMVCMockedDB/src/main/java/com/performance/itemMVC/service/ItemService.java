package com.performance.itemMVC.service;

import java.util.List;

import com.performance.itemMVC.domain.Item;



public interface ItemService {
	
	public List<Item> findByCategory(String category);
//	public Mono<Item> createItem(Item item);	
//	public Flux<Item> createAllItems(Flux<Item> items);

}
