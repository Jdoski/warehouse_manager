package com.skillstorm.warehouse_manager.compositeKeys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventoryId implements Serializable {
    @Column(name="warehouse_id")
    private int warehouse_id;

    @Column(name="item_id")
    private int item_id;

    
    public InventoryId(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public InventoryId() {
    }

    public InventoryId(int warehouse_id, int item_id) {
        this.warehouse_id = warehouse_id;
        this.item_id = item_id;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + warehouse_id;
        result = prime * result + item_id;
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
        InventoryId other = (InventoryId) obj;
        if (warehouse_id != other.warehouse_id)
            return false;
        if (item_id != other.item_id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "InvetoryId [warehouse_id=" + warehouse_id + ", item_id=" + item_id + "]";
    }

    
    
}
