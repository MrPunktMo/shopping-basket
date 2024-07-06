package org.triplem.shoppingbasket.basket.component.voucher;

import org.springframework.stereotype.Component;
import org.triplem.shoppingbasket.basket.component.basket.ItemWrapper;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;
import java.util.Objects;

@Component
public class Buy1Get2FreeVoucher implements VoucherRule{

    @Override
    public Voucher.TypeEnum getVoucherType() {
        return Voucher.TypeEnum.BUY1GET1FORFREE;
    }

    @Override
    public List<ItemWrapper> processRule(Voucher voucher, List<ItemWrapper> items) {

        if(items.stream().map(itemWrapper -> itemWrapper.getItem().getId()).anyMatch(id -> Objects.equals(id, voucher.getItemId()))) {
            items.stream()
                    .filter(itemWrapper -> Objects.equals(itemWrapper.getItem().getId(), voucher.getItemId())).findFirst()
                    .ifPresent(itemWrapper -> itemWrapper.getItem().setPrice(calculatePrice(itemWrapper)));
        }

        return items;

    }

    private float calculatePrice(ItemWrapper itemWrapper) {
        return ((itemWrapper.getItem().getPrice() * (itemWrapper.getCount() / 2)) + (itemWrapper.getItem().getPrice() * (itemWrapper.getCount() % 2)))
                / itemWrapper.getCount();
    }

}
