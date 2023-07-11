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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.warehouse_manager.models.Warehouse;
import com.skillstorm.warehouse_manager.repositories.WarehouseRepository;
import com.skillstorm.warehouse_manager.services.WarehouseService;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin("http://127.0.0.1:5500")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;
    
    @Autowired
    WarehouseRepository warehouseRepository;

    //Basic get mapping for default url to return all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses(){

        List<Warehouse> warhouses = warehouseService.findAllWarehouses();
        return new ResponseEntity<List<Warehouse>>(warhouses, HttpStatus.OK);
    }
    // Getting a single warehouse by the id
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable int id){
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        return new ResponseEntity<Warehouse>(warehouse,HttpStatus.OK);
    }
    // Default adding new warehouse endpoint
    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){

        Warehouse newWarehouse = warehouseService.saveWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }

    @PutMapping("/warehouse/updateCapacity")
    public ResponseEntity<Integer> updateWarehouseCapacity(@RequestBody Warehouse warehouse, @RequestParam int newCapacity){
        
        int updated = warehouseService.updateWarehouseCapacity(warehouse, newCapacity);
        return new ResponseEntity<Integer>(updated, HttpStatus.OK);
    
    }

    @DeleteMapping("warehouse/{id}")
    public ResponseEntity<HttpStatus> deleteWarehouse(@PathVariable("id") int id){
        try{
            warehouseRepository.deleteById(id);
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
