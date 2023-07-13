package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.warehouse_manager.models.Item;
import com.skillstorm.warehouse_manager.repositories.ItemRepository;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Item findItemById(int id){
        Optional<Item> item = itemRepository.findById(id);

        if(item.isPresent()){
            return item.get();
        }

        return null;
    }
    //Basic save functionality, still incomplete
    public Item saveItem(Item item){
        return itemRepository.save(item);
    }

    //basic update functionality to change the number of items allowed
    public int updateItemCost(Item item, int cost){
        return itemRepository.updateItemCost(item.getId(), cost);
    }
}
