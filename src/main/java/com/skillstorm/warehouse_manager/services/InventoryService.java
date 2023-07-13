package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_manager.models.Inventory;
import com.skillstorm.warehouse_manager.repositories.InventoryRepository;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public List<Inventory> findAllInventories(){
        return inventoryRepository.findAll();
    }

    public Inventory findInventoryById(int id){
        Optional<Inventory> inventory = inventoryRepository.findById(id);

        if(inventory.isPresent()){
            return inventory.get();
        }

        return null;
    }
    //Basic save functionality, still incomplete
    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }


}
