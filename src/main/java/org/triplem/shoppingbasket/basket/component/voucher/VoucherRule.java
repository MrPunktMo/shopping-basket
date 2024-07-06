package org.triplem.shoppingbasket.basket.component.voucher;

import org.triplem.shoppingbasket.basket.component.basket.ItemWrapper;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;

public interface VoucherRule {

    Voucher.TypeEnum getVoucherType();

    List<ItemWrapper> processRule(Voucher voucher, List<ItemWrapper> items);

}
