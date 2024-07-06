package org.triplem.shoppingbasket.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.triplem.shoppingbasket.basket.service.BasketService;
import org.triplem.shoppingbasket.basket.service.TotalPriceService;
import org.triplem.shoppingbasket.basket.service.VoucherService;
import org.triplem.shoppingbasket.models.Item;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TotalPriceServiceTests {

    @Autowired
    BasketService basketService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    TotalPriceService totalPriceService;

    List<Item> items;
    List<Voucher> vouchers;

    @BeforeEach
    void init() {

        Item item1_1 = new Item();
        item1_1.setId(1L);
        item1_1.setName("Item Test 1");
        item1_1.setPrice(10f);

        Item item2_1 = new Item();
        item2_1.setId(1L);
        item2_1.setName("Item Test 1");
        item2_1.setPrice(10f);

        Item item3_2 = new Item();
        item3_2.setId(2L);
        item3_2.setName("Item Test 2");
        item3_2.setPrice(20f);

        items = List.of(item1_1, item2_1, item3_2);

        Voucher voucher1Free = new Voucher();
        voucher1Free.setId(1L);
        voucher1Free.setItemId(1L);
        voucher1Free.setType(Voucher.TypeEnum.BUY1GET1FORFREE);

        Voucher voucherPercentage = new Voucher();
        voucherPercentage.setId(1L);
        voucherPercentage.setItemId(2L);
        voucherPercentage.setType(Voucher.TypeEnum._10PERCENTOFARTICLE);

        vouchers = List.of(voucher1Free, voucherPercentage);

    }

    @Test
    @DisplayName("Get total prized of reduced items")
    void getTotalPricesWithReducedItems() {

        Integer basketId = basketService.createBasket();
        assertNotNull(basketId);

        items.forEach(item -> basketService.addItemToBasket(basketId, item));
        vouchers.forEach(voucher -> voucherService.addVoucherToBasket(basketId, voucher));

        Float totalPrice = totalPriceService.getTotalPriceOfBasket(basketId);
        assertNotNull(totalPrice);
        //2 * 10 (one off - item1) --> 10
        //1 * 20 (10 percent off) --> 18
        //Total: 28
        assertEquals(28f, totalPrice);

    }


}
