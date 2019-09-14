package com.performance.itemMVC.repository;

import java.util.List;

import com.performance.itemMVC.domain.Item;

public interface ItemRepository {

	public List<Item> findByCategory(String category);

}
