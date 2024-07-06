package org.triplem.shoppingbasket.basket.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.triplem.shoppingbasket.basket.component.VoucherRule;
import org.triplem.shoppingbasket.models.Item;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VoucherService {

    private final Map<Integer, Set<Voucher>> vouchersToBaskets = new HashMap<>();
    private final Map<Voucher.TypeEnum, VoucherRule> voucherRules;

    public VoucherService(VoucherRule... voucherRulesArray) {
        this.voucherRules = Arrays.stream(voucherRulesArray).collect(Collectors.toMap(
            VoucherRule::getVoucherType, Function.identity()
        ));
    }

    public void addVoucherToBasket(Integer basketId, Voucher voucher) {

        if(basketId == null || voucher == null)
            log.warn("Could not add voucher to basket as one argument was null");
        else if(vouchersToBaskets.containsKey(basketId))
            vouchersToBaskets.get(basketId).add(voucher);
        else {
            Set<Voucher> vouchers = new HashSet<>();
            vouchers.add(voucher);
            vouchersToBaskets.put(basketId, vouchers);
        }
    }

    public List<Item> applyVouchersToBasket(Integer basketId, List<Item> items) {

        if(vouchersToBaskets.containsKey(basketId)) {
            vouchersToBaskets.get(basketId).forEach(voucher -> voucherRules.get(voucher.getType()).processRule(voucher, items));
        }

        return items;

    }

}
