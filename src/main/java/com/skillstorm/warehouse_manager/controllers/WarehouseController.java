package com.skillstorm.warehouse_manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses(){
        List<Warehouse> warhouses = warehouseService.findAllWarehouses();
        return new ResponseEntity<List<Warehouse>>(warhouses, HttpStatus.OK);
    }
}
