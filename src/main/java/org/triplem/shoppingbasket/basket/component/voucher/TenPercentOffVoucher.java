package org.triplem.shoppingbasket.basket.component.voucher;

import org.springframework.stereotype.Component;
import org.triplem.shoppingbasket.basket.component.basket.ItemWrapper;
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
    public List<ItemWrapper> processRule(Voucher voucher, List<ItemWrapper> items) {

        if(items.stream().map(itemWrapper -> itemWrapper.getItem().getId()).anyMatch(id -> Objects.equals(id, voucher.getItemId()))) {
            items.stream()
                    .filter(itemWrapper -> Objects.equals(itemWrapper.getItem().getId(), voucher.getItemId()))
                    .forEach(
                            itemWrapper -> itemWrapper.getItem().setPrice(itemWrapper.getItem().getPrice() * 0.9f)
                    );
        }

        return items;
    }
}
