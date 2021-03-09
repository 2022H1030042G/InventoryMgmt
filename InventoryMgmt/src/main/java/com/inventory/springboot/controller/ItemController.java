package com.inventory.springboot.controller;

import com.inventory.springboot.model.Item;
import com.inventory.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	// display list of items
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listItem", itemService.getAllItem());
		return "index";
	}
	
	@GetMapping("/showNewItemForm")
	public String showNewItemForm(Model model) {
		// create model attribute to bind form data
		Item item = new Item();
		model.addAttribute("item", item);
		return "new_item";
	}
	
	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute("item") Item item) {
		// save item to database
		itemService.saveItem(item);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get item from the service
		Item item = itemService.getItemById(id);
		
		// set item as a model attribute to pre-populate the form
		model.addAttribute("item", item);
		return "update_item";
	}
	
	@GetMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable (value = "id") long id) {
		
		// call delete item method 
		this.itemService.deleteItemById(id);
		return "redirect:/";
	}

	@GetMapping("/addtoCart/{id}")
	public String addtoCart(@PathVariable(value = "id") long id, Model model) {
		Item item = itemService.getItemById(id);
		model.addAttribute("item", item);
		return "cart";
	}

	@GetMapping("/checkout/{id}")
	public String checkout(@PathVariable(value = "id") long id, Model model){
		Item item = itemService.getItemById(id);
		model.addAttribute("item", item);
		return "checkout";
	}
}
