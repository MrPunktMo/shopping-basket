package org.triplem.shoppingbasket.basket.component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.triplem.shoppingbasket.models.Item;

import java.util.*;

@RequiredArgsConstructor
@Getter
public class Basket {

    private final Integer basketId;
    private final Map<Item, Integer> items = new HashMap<>();

    public void addItem(Item item) {
        if(!Objects.isNull(item)) {
            if(items.containsKey(item))
                items.replace(item, items.get(item) + 1);
            else
                items.put(item, 1);
        }
    }

    public void removeItem(Item item) {
        if(!Objects.isNull(item) && items.containsKey(item))
            items.replace(item, items.get(item) - 1);
    }

    public List<Item> getAllItems() {
        return items.entrySet().stream()
                .filter(itemEntry -> itemEntry.getValue() > 0)
                .map(Map.Entry::getKey)
                .toList();
    }

}
