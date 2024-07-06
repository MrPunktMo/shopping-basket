package org.triplem.shoppingbasket.basket.component.basket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.triplem.shoppingbasket.models.Item;

import java.util.*;

@RequiredArgsConstructor
@Getter
public class Basket {

    private final Integer basketId;
    private final Map<Long, ItemWrapper> items = new HashMap<>();

        public void addItem(Item item) {
        if(!Objects.isNull(item)) {
            if(items.containsKey(item.getId()))
                items.get(item.getId()).increaseCount();
            else
                items.put(item.getId(), new ItemWrapper(item, 1));
        }
    }

    public void removeItem(Item item) {
        if(!Objects.isNull(item) && items.containsKey(item.getId()))
            items.get(item.getId()).decreaseCount();
    }

    public List<ItemWrapper> getAllItems() {
        return items.values().stream().toList();
    }

}
