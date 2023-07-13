package com.skillstorm.warehouse_manager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.warehouse_manager.compositeKeys.InventoryId;
import com.skillstorm.warehouse_manager.models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId>{
    
}
