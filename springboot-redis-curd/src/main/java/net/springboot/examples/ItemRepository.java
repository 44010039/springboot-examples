package net.springboot.examples;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    public static final String KEY = "ITEM";
    private HashOperations<String, Integer, Item> hashOperations;

    public ItemRepository(RedisTemplate<String, Item> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    /*Getting all Items from tSable*/
    public Map<Integer,Item> getAllItems(){
        return hashOperations.entries(KEY);
    }

    /*Getting a specific item by item id from table*/
    public Item getItem(int itemId){
        return (Item) hashOperations.get(KEY,itemId);
    }

    /*Adding an item into redis database*/
    public void addItem(Item item){
        hashOperations.put(KEY,item.getId(),item);
    }

    /*delete an item from database*/
    public void deleteItem(int id){
        hashOperations.delete(KEY,id);
    }

    /*update an item from database*/
    public void updateItem(Item item){
        addItem(item);
    } 
}
