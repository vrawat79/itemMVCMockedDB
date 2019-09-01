package com.performance.itemMVC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.performance.itemMVC.domain.Item;
import com.performance.itemMVC.repository.ReactiveItemRepository;



@Service
public class ReactiveItemService implements ItemService {

	@Autowired
	private ReactiveItemRepository reactiveItemRepo;

//	@Override
	public List<Item> findByCategory(String category) {
		// TODO Auto-generated method stub
		return reactiveItemRepo.findByCategory(category);
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
	
	public static void main(String[] args) {
		
		boolean flag = false;
		
		if(flag) {
			System.out.println("flag is true");
		}else if(flag) {
			System.out.println("flag is true2");
		}else if(flag) {
			System.out.println("flag is true");
		}else {
			System.out.println("flag is false");
		}
			
	}

}
