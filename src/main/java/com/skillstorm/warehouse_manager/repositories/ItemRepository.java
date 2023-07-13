package com.skillstorm.warehouse_manager.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.warehouse_manager.models.Item;

@Repository
public interface ItemRepository  extends JpaRepository<Item, Integer>{
    @Query("update Item i set i.cost = :cost where id = :item_id")
    @Modifying // any queries that do inserts/updates/ or deletes need this annotation - anything with @Modifying has to return void or int
    @Transactional  // for transaction management in spring boot
    public int updateItemCost(@Param("item_id") int id, @Param("cost") int cost);
}
