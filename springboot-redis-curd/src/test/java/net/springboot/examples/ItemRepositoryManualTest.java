package net.springboot.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RedisCurdApplication.class)
public class ItemRepositoryManualTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void whenSavingItem_thenAvailableOnRetrieval() throws Exception {
        final Item item = new Item(1, "name1", "category1");
        itemRepository.addItem(item);
        final Item retrievedItem = itemRepository.getItem(1);
        assertEquals(item.getId(), retrievedItem.getId());
    }

    @Test
    public void whenUpdatingItem_thenAvailableOnRetrieval() throws Exception {
        final Item item = new Item(2, "name2", "category2");
        itemRepository.addItem(item);
        item.setName("Richard Watson");
        itemRepository.updateItem(item);
        final Item retrievedItem = itemRepository.getItem(item.getId());
        assertEquals(item.getName(), retrievedItem.getName());
    }

    @Test
    public void whenSavingItems_thenAllShouldAvailableOnRetrieval() throws Exception {
        final Item engItem = new Item(3, "name3", "category3");
        final Item medItem = new Item(4, "name4", "category4");
        itemRepository.addItem(engItem);
        itemRepository.addItem(medItem);
        List<Item> items = new ArrayList<>();
        itemRepository.getAllItems().forEach((k, v) -> items.add(v));
        assertTrue(items.size() >= 2);
    }

    @Test
    public void whenDeletingItem_thenNotAvailableOnRetrieval() throws Exception {
        final Item item = new Item(5, "name5", "category5");
        itemRepository.addItem(item);
        itemRepository.deleteItem(item.getId());
        final Item retrievedItem = itemRepository.getItem(item.getId());
        assertNull(retrievedItem);
    }  
}
