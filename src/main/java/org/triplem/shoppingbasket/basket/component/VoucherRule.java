package org.triplem.shoppingbasket.basket.component;

import org.triplem.shoppingbasket.models.Item;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;

public interface VoucherRule {

    Voucher.TypeEnum getVoucherType();

    List<Item> processRule(Voucher voucher, List<Item> items);

}
