package com.performance.itemMVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.performance.itemMVC.domain.Item;
import com.performance.itemMVC.repository.ItemRepository;

import reactor.core.publisher.Flux;

@Service
public class MVCItemService implements ItemService {

	@Autowired
//	private BlockingItemRepository reactiveItemRepo;
	private ItemRepository itemRepo;

//	@Override
	public Flux<Item> findByCategory(String category) {
		// TODO Auto-generated method stub
		return itemRepo.findByCategory(category);
	}

//	@Override
//	public Flux<Item> createAllItems(Flux<Item> items) {
//		// TODO Auto-generated method stub
//		return reactiveItemRepo.saveAll(items);
//	}
//
//	@Override
//	public Mono<Item> createItem(Item item) {
//		// TODO Auto-generated method stub
//		return reactiveItemRepo.save(item);
//	}

}
