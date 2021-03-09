package com.inventory.springboot.service;
import java.util.List;

import com.inventory.springboot.model.Item;

public interface ItemService {
	List<Item> getAllItem();
	void saveItem(Item item);
	Item getItemById(long id);
	void deleteItemById(long id);
}