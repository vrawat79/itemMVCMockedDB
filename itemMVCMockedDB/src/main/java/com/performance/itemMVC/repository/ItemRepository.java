package com.performance.itemMVC.repository;

import java.util.List;

import com.performance.itemMVC.domain.Item;

import reactor.core.publisher.Flux;

public interface ItemRepository {

//	public List<Item> findByCategory(String category);
	public Flux<Item> findByCategory(String category);

}
