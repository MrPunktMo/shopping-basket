package org.triplem.shoppingbasket.basket.component;

import org.springframework.stereotype.Component;
import org.triplem.shoppingbasket.models.Item;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;
import java.util.Objects;

@Component
public class TenPercentOffVoucher implements VoucherRule {

    @Override
    public Voucher.TypeEnum getVoucherType() {
        return Voucher.TypeEnum._10PERCENTOFARTICLE;
    }

    @Override
    public List<Item> processRule(Voucher voucher, List<Item> items) {

        if(items.stream().map(Item::getId).anyMatch(id -> Objects.equals(id, voucher.getItemId()))) {
            items.stream()
                    .filter(item -> Objects.equals(item.getId(), voucher.getItemId()))
                    .forEach(
                            item -> item.setPrice(item.getPrice() * 0.9f)
                    );
        }

        return items;
    }
}
