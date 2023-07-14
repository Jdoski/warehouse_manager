package com.skillstorm.warehouse_manager.models;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.skillstorm.warehouse_manager.compositeKeys.InventoryId;

@Entity
@Table(name="inventories", schema="public")
public class Inventory {

    @EmbeddedId
    private InventoryId id;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName ="id", insertable=false, updatable = false)
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Item item;

    public Inventory() {
    }

    public Inventory(InventoryId id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Inventory(InventoryId id, int quantity, Warehouse warehouse, Item item) {
        this.id = id;
        this.quantity = quantity;
        this.warehouse = warehouse;
        this.item = item;
    }

    public Inventory(int quantity, Warehouse warehouse, Item item) {
        this.quantity = quantity;
        this.warehouse = warehouse;
        this.item = item;
    }

    public InventoryId getId() {
        return id;
    }

    public void setId(InventoryId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
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
        Inventory other = (Inventory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantity != other.quantity)
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", quantity=" + quantity + ", warehouse=" + warehouse + ", item=" + item + "]";
    }

    
    
    
}
