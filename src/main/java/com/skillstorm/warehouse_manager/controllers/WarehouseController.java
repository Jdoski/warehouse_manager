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

import com.skillstorm.warehouse_manager.models.Warehouse;
import com.skillstorm.warehouse_manager.repositories.WarehouseRepository;
import com.skillstorm.warehouse_manager.services.WarehouseService;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;
    
    @Autowired
    WarehouseRepository warehouseRepository;

    //Basic get mapping for default url to return all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses(){

        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        return new ResponseEntity<List<Warehouse>>(warehouses, HttpStatus.OK);
    }
    // Getting a single warehouse by the id
    @GetMapping("/warehouse/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable int id){
        Warehouse warehouse = warehouseService.findWarehouseById(id);
        return new ResponseEntity<Warehouse>(warehouse,HttpStatus.OK);
    }
    //Default adding new warehouse endpoint
    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){

        Warehouse newWarehouse = warehouseService.createWarehouse(warehouse);
        return new ResponseEntity<Warehouse>(newWarehouse, HttpStatus.CREATED);
    }

    @PutMapping("/warehouse/updateWarehouse/{id}/{max_items}/{location}")
    public ResponseEntity<Warehouse> updateWarehouse(
        @PathVariable("id") int id,
        @PathVariable("max_items") int max_items, 
        @PathVariable("location") String location){

        if(warehouseService.findWarehouseById(id) != null){
            warehouseService.updateWarehouse(id, max_items, location);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(warehouseService.findWarehouseById(id));
        }
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON).body(warehouseService.findWarehouseById(id));
        
    
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
