package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_manager.compositeKeys.InventoryId;
import com.skillstorm.warehouse_manager.models.Inventory;
import com.skillstorm.warehouse_manager.repositories.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    ItemService itemService;

    public List<Inventory> findAllInventories(){
        return inventoryRepository.findAll();
    }

    //function used to check if an inventory with a warehouseId and itemId exists already.
    
    public boolean productExists(int warehouseId, int itemId) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        return inventoryRepository.existsById(inventoryId);
    }

    // The only item allowed to update in the Inventory is the quantity
    // Here we update the qunatity of the inventory based on the warehouseId and itemId that was passed
    // The quantity is added to the invetory and saved back to the database
    public void updateProductQuantity(int warehouseId, int itemId, int quantity) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);
    
        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        } else {
            ResponseEntity.badRequest();
        }
    }

    public Optional<Inventory> findInventoryById(int warehouseId, int itemId) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        if(optionalInventory.isPresent()){
            return optionalInventory;
        }
        return null;
    }
    //Basic save functionality, still incomplete
    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    // standard creation of an inventory, uses the warehouseId and the itemId to create the key
    // then saves the new inventory into the database
    public void createInventory(int warehouseId, int itemId, int quantity) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        Inventory inventory = new Inventory(inventoryId, quantity);
        inventoryRepository.save(inventory);
    }


}
