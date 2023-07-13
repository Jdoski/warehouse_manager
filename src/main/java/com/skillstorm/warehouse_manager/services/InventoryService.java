package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public boolean productExists(int warehouseId, int itemId) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        return inventoryRepository.existsById(inventoryId);
    }

    public void updateProductQuantity(int warehouseId, int itemId, int quantity) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        Inventory inventory = inventoryRepository.getById(inventoryId);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }

    public Inventory findInventoryById(int warehouseId, int itemId) {
        InventoryId inventoryId = new InventoryId(warehouseId, itemId);
        return inventoryRepository.getById(inventoryId);
    }
    //Basic save functionality, still incomplete
    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }


}
