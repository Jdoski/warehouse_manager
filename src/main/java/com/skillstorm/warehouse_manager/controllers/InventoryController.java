package com.skillstorm.warehouse_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_manager.models.Inventory;
import com.skillstorm.warehouse_manager.repositories.InventoryRepository;
import com.skillstorm.warehouse_manager.services.InventoryService;

@RestController
@RequestMapping("/inventories")
@CrossOrigin("http://127.0.0.1:5500")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;    

    @Autowired
    InventoryRepository inventoryRepository;

    //Basic get mapping for default url to return all inventories
    @GetMapping
    public ResponseEntity<List<Inventory>> findAllInventoriess(){

        List<Inventory> inventories = inventoryService.findAllInventories();
        return new ResponseEntity<List<Inventory>>(inventories, HttpStatus.OK);
    }
    // Getting a single inventory by the id
    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> findInventoryById(@PathVariable int id){
        Inventory inventory = inventoryService.findInventoryById(id);
        return new ResponseEntity<Inventory>(inventory,HttpStatus.OK);
    }
    // Default adding new inventory endpoint
    @PostMapping("/inventory")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory){

        Inventory newInventory = inventoryService.saveInventory(inventory);
        return new ResponseEntity<Inventory>(newInventory, HttpStatus.CREATED);
    }

    @DeleteMapping("inventory/{id}")
    public ResponseEntity<HttpStatus> deleteInventory(@PathVariable("id") int id){
        try{
            inventoryRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}