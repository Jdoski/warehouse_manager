package com.skillstorm.warehouse_manager.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Items")
public class Item {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private int cost;

    @Column
    private Set<Warehouse> warehouses;

    public Item() {

    }

    public Item(String name, String type, int cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public Item(int id, String name, String type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    
    }

    public Item(int id, String name, String type, int cost, Set<Warehouse> warehouses) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.warehouses = warehouses;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

      public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + cost;
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
        Item other = (Item) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (type != other.type)
            return false;
        if (cost != other.cost)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", type=" + type + ", cost=" + cost + ", stats=";
    }

  

    

    
}
