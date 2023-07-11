package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_manager.models.Warehouse;
import com.skillstorm.warehouse_manager.repositories.WarehouseRepository;

@Service
public class WarehouseService {
    
    @Autowired
    WarehouseRepository warehouseRepository;

    public List<Warehouse> findAllWarehouses(){
        return warehouseRepository.findAll();
    }

    public Warehouse findWarehouseById(int id){
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if(warehouse.isPresent()){
            return warehouse.get();
        }

        return null;
    }
    //Basic save functionality, still incomplete
    public Warehouse saveWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    //basic update functionality to change the number of items allowed
    public int updateWarehouseCapacity(Warehouse warehouse, int max_items){
        return warehouseRepository.updateWarehouseCapacity(warehouse.getId(), max_items);
    }

}
