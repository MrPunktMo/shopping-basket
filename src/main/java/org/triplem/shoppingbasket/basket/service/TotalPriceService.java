package org.triplem.shoppingbasket.basket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.triplem.shoppingbasket.models.Item;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class TotalPriceService {
    
    private final BasketService basketService;
    private final VoucherService voucherService;

    public Float getTotalPriceOfBasket(Integer basketId) {

        if(Objects.isNull(basketId)) {
            log.warn("Cannot get total price without basketId!");
            return 0f;
        } else {
            return voucherService
                    .applyVouchersToBasket(basketId, basketService.getAllItemsInBasket(basketId)).stream()
                    .map(Item::getPrice)
                    .reduce(Float::sum)
                    .orElse(0f);
        }
    }

}
