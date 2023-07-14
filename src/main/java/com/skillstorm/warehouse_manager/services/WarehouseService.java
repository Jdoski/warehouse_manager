package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Warehouse createWarehouse(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }

    public void updateWarehouse(int id, int max_items, String location){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        if (optionalWarehouse.isPresent()){
            Warehouse warehouse = optionalWarehouse.get();
            warehouse.setLocation(location);
            warehouse.setMax_items(max_items);
            warehouseRepository.save(warehouse);
        }else{
            ResponseEntity.badRequest();
        }
    }

    // public int updateWarehouse(int warehouse_id, int max_items, String location) {
    //     return warehouseRepository.updateWarehouse(warehouse_id, max_items, location);
    // }
}
