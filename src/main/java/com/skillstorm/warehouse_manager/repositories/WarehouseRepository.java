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

    @Query("update Warehouse w set w.max_items = :max_items where id = :warehouse_id")
    @Modifying // any queries that do inserts/updates/ or deletes need this annotation - anything with @Modifying has to return void or int
    @Transactional  // for transaction management in spring boot
    public int updateWarehouseCapacity(@Param("warehouse_id") int id, @Param("max_items") int max_items);

    
}
