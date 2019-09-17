package com.performance.itemMVC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.performance.itemMVC.domain.Item;
import com.performance.itemMVC.service.ItemService;

import reactor.core.publisher.Flux;



@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping(path = "/items/{category}")
	public Flux<Item> getItemsbyCategory(@PathVariable String category) {

		return itemService.findByCategory(category);
	}
//	@PostMapping(path = "/items")
//	public List<Item> createBulkItems(@RequestBody List<Item> itemFlux) {
//
//		return itemService.createAllItems(itemFlux);
//	}

}
