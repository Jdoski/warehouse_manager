package com.skillstorm.warehouse_manager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skillstorm.warehouse_manager.models.Item;
import com.skillstorm.warehouse_manager.models.Warehouse;
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

    public void updateItem(int id, String name, String type, int cost){
        Optional<Item> optionalItem = itemRepository.findById(id);

        if(optionalItem.isPresent()){
            Item item = optionalItem.get();
            item.setName(name);
            item.setType(type);
            item.setCost(cost);
            itemRepository.save(item);
        }else{
            ResponseEntity.badRequest();
        }
    }

    //basic update functionality to change the number of items allowed
    public int updateItemCost(Item item, int cost){
        return itemRepository.updateItemCost(item.getId(), cost);
    }

    public void deleteItem(Item item){
        itemRepository.delete(item);
    }
}
