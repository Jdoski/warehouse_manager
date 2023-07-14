package com.skillstorm.warehouse_manager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.skillstorm.warehouse_manager.compositeKeys.InventoryId;
import com.skillstorm.warehouse_manager.models.Inventory;
import com.skillstorm.warehouse_manager.repositories.InventoryRepository;
import com.skillstorm.warehouse_manager.services.InventoryService;

@RestController
@RequestMapping("/inventories")
@CrossOrigin
public class InventoryController {

    @Autowired
    InventoryService inventoryService;    

    @Autowired
    InventoryRepository inventoryRepository;

    InventoryId inventory;

    //Basic get mapping for default url to return all inventories
    @GetMapping
    public ResponseEntity<List<Inventory>> findAllInventoriess(){

        List<Inventory> inventories = inventoryService.findAllInventories();
        return new ResponseEntity<List<Inventory>>(inventories, HttpStatus.OK);
    }
    // Getting a single inventory by the id
    @GetMapping("/inventory/{warehouseId}{itemId}")
    public ResponseEntity<Optional<Inventory>> findInventoryById(@PathVariable int warehouseId, @PathVariable int itemId){
        Optional<Inventory> inventory = inventoryService.findInventoryById(warehouseId,itemId);
        return new ResponseEntity<Optional<Inventory>>(inventory,HttpStatus.OK);
    }
    // Default adding new inventory endpoint
    @PostMapping("/inventory")
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory){    
        
        Inventory newInventory = inventoryService.saveInventory(inventory);
        return new ResponseEntity<Inventory>(newInventory, HttpStatus.CREATED);
    }
    
    @PostMapping("/inventory/{warehouse_id}/{item_id}")
    public ResponseEntity<String> createOrUpdateInventory(
            @PathVariable("warehouse_id") int warehouse_id,
            @PathVariable("item_id") int item_id,
            @RequestParam("quantity") int quantity) {
            
        if (inventoryService.productExists(warehouse_id, item_id)) {
            // Inventory exists, perform a PUT request to update the quantity
            inventoryService.updateProductQuantity(warehouse_id, item_id, quantity);
            return ResponseEntity.ok("Quantity updated successfully");
        } else {
            // Inventory doesn't exist, create a new entry
            inventoryService.createInventory(warehouse_id, item_id, quantity);
            return ResponseEntity.ok("Inventory created successfully");
        }
    }
    @PutMapping("/inventory/updateInventory/{warehouse_id}/{item_id}/{quantity}")
    public ResponseEntity<String> updateQuantity(
            @PathVariable("warehouse_id") int warehouse_id,
            @PathVariable("item_id") int item_id,
            @PathVariable("quantity") int quantity) {
            
        if (!inventoryService.productExists(warehouse_id, item_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        
        inventoryService.updateProductQuantity(warehouse_id, item_id, quantity);
        
        return ResponseEntity.ok("Quantity updated successfully");
    }

    @DeleteMapping("inventory/{warehouse_id}{item_id}")
    public ResponseEntity<HttpStatus> deleteInventory(@PathVariable("warehouse_id") int warehouse_id, @PathVariable("item_id") int item_id){
        InventoryId inventoryId =  new InventoryId(warehouse_id,item_id);
        try{
            inventoryRepository.deleteById(inventoryId);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
