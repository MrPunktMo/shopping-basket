package org.triplem.shoppingbasket.basket.component;

import org.springframework.stereotype.Component;
import org.triplem.shoppingbasket.models.Item;
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
    public List<Item> processRule(Voucher voucher, List<Item> items) {

        if(items.stream().map(Item::getId).anyMatch(id -> Objects.equals(id, voucher.getItemId()))) {
            items.stream()
                    .filter(item -> Objects.equals(item.getId(), voucher.getItemId()))
                    .limit(items.stream().filter(item -> Objects.equals(item.getId(), voucher.getItemId())).count() / 2)
                    .forEach(
                            item -> item.setPrice(0.0f)
                    );
        }

        return items;

    }
}
