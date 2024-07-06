package org.triplem.shoppingbasket.basket.component.basket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.triplem.shoppingbasket.models.Item;

@AllArgsConstructor
@Getter
public class ItemWrapper {

    private final Item item;
    private Integer count;

    public void increaseCount() {
        count++;
    }

    public void decreaseCount() {
        count--;
    }

}
