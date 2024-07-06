package org.triplem.shoppingbasket.basket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.triplem.shoppingbasket.basket.component.basket.Basket;
import org.triplem.shoppingbasket.basket.component.basket.ItemWrapper;
import org.triplem.shoppingbasket.models.Item;

import java.util.*;

@Service
@Slf4j
public class BasketService {

    private final Map<Integer, Basket> baskets = new HashMap<>();
    private final Random random = new Random();

    public Integer createBasket() {
        Integer randomId = Objects.hash(random.ints());
        baskets.put(randomId, new Basket(randomId));
        return randomId;
    }

    public void addItemToBasket(Integer basketId, Item item) {

        if(basketId == null || item == null)
            log.warn("Cannot add item, basketId or item were null!");
        else if(!baskets.containsKey(basketId))
            log.warn("Cannot add item, basketId " + basketId + " does not exist!");
        else
            baskets.get(basketId).addItem(item);

    }

    public void removeItemFromBasket(Integer basketId, Item item) {

        if(basketId == null || item == null)
            log.warn("Cannot remove item, basketId or item were null!");
        else if(!baskets.containsKey(basketId))
            log.warn("Cannot remove item, basketId " + basketId + " does not exist!");
        else
            baskets.get(basketId).removeItem(item);
    }

    public List<ItemWrapper> getAllItemsInBasket(Integer basketId) {

        if(basketId == null) {
            log.warn("Cannot get items, basketId was not provided!");
            return Collections.emptyList();
        } else if (!baskets.containsKey(basketId)) {
            log.warn("Cannot get items, basketId " + basketId + " does not exist!");
            return Collections.emptyList();
        } else
            return baskets.get(basketId).getAllItems();

    }

}
