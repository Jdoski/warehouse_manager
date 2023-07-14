package com.skillstorm.warehouse_manager.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.warehouse_manager.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

@Query("update Warehouse w set w.max_items = :max_items, w.location = :location where w.id = :id")
@Modifying
@Transactional
public int updateWarehouse(@Param("id") int id, @Param("max_items") int maxItems, @Param("location") String location);

    
}
