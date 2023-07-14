package com.skillstorm.warehouse_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_manager.models.Item;
import com.skillstorm.warehouse_manager.models.Warehouse;
import com.skillstorm.warehouse_manager.repositories.ItemRepository;
import com.skillstorm.warehouse_manager.services.ItemService;


@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> findAllItems(){

        List<Item> item = itemService.findAllItems();
        return new ResponseEntity<List<Item>>(item, HttpStatus.OK);
    }
    
     // Getting a single item by the id
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable int id){
        Item item = itemService.findItemById(id);
        return new ResponseEntity<Item>(item,HttpStatus.OK);
    }
    // Default adding new item endpoint
    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item){

        Item newItem = itemService.saveItem(item);
        return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
    }

    @PutMapping("/item/updateCost")
    public ResponseEntity<Integer> updateItemCost(@RequestBody Item item, @RequestParam int newCost){
        
        int updated = itemService.updateItemCost(item, newCost);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    
    }

     @PutMapping("/item/updateItem/{id}/{name}/{type}/{cost}")
    public ResponseEntity<Item> updateWarehouse(
        @PathVariable("id") int id,
        @PathVariable("name") String name, 
        @PathVariable("type") String type,
        @PathVariable("cost") int cost){

        if(itemService.findItemById(id) != null){
            itemService.updateItem(id, name, type, cost);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(itemService.findItemById(id));
        }
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON).body(itemService.findItemById(id));
        
    
    }



    @DeleteMapping("item/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") int id){
        try{
            itemRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
