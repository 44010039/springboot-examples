package net.springboot.examples;

import java.io.Serializable;

import lombok.Data;

@Data
public class Item implements Serializable {
    private int id;
    private String name;
    private String category;
    
    public Item() {
    }

    public Item(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    
}
