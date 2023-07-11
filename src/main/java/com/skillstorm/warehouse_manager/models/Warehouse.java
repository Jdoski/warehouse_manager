package com.skillstorm.warehouse_manager.models;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Warehouses")
public class Warehouse {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String location;

    @Column
    private int max_items;

    @Column
    private Set<Item> items;

    public Warehouse() {

    }

    public Warehouse(String location, int max_items) {
        this.location = location;
        this.max_items = max_items;
    }

    public Warehouse(int id, String location, int max_items) {
        this.id = id;
        this.location = location;
        this.max_items = max_items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getMax_items() {
        return max_items;
    }

    public void setMax_items(int max_items) {
        this.max_items = max_items;
    }

    public Set<Item> get_items(){
        return items;
    }
    public void setItems(Set<Item> items){
        this.items = items;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((location == null) ? 0 : location.hashCode());
        result = prime * result + max_items;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Warehouse other = (Warehouse) obj;
        if (id != other.id)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (max_items != other.max_items)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Warehouse [id=" + id + ", location=" + location + ", max_items=" + max_items + "]";
    }

    
    
}
