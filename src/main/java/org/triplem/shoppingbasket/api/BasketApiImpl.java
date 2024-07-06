package org.triplem.shoppingbasket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.triplem.shoppingbasket.basket.component.basket.ItemWrapper;
import org.triplem.shoppingbasket.basket.service.BasketService;
import org.triplem.shoppingbasket.basket.service.TotalPriceService;
import org.triplem.shoppingbasket.basket.service.VoucherService;
import org.triplem.shoppingbasket.models.Item;
import org.triplem.shoppingbasket.models.Voucher;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class BasketApiImpl implements BasketApi {
    private final BasketService basketService;

    private final VoucherService voucherService;
    private final TotalPriceService totalPriceService;

    @Override
    public ResponseEntity<Integer> postBasket() {
        return ResponseEntity.ok(basketService.createBasket());
    }

    @Override
    public ResponseEntity<Void> addItemToBasket(Integer basketId, Item item) {

        if(Objects.isNull(basketId) || Objects.isNull(item))
            return ResponseEntity.badRequest().build();
        else {
            basketService.addItemToBasket(basketId, item);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public ResponseEntity<Void> addVoucherToBasket(Integer basketId, Voucher voucher) {

        if(Objects.isNull(basketId) || Objects.isNull(voucher))
            return ResponseEntity.badRequest().build();
        else {
            voucherService.addVoucherToBasket(basketId, voucher);
            return ResponseEntity.ok().build();
        }
    }

    @Override
    public ResponseEntity<List<Item>> getAllItemsInBasket(Integer basketId) {

        if(Objects.isNull(basketId))
            return ResponseEntity.badRequest().build();
        else {
            return ResponseEntity.ok(basketService.getAllItemsInBasket(basketId).stream().map(ItemWrapper::getItem).toList());
        }

    }

    @Override
    public ResponseEntity<Float> getTotalPriceOfBasket(Integer basketId) {

        if(Objects.isNull(basketId))
            return ResponseEntity.badRequest().build();
        else
            return ResponseEntity.ok(totalPriceService.getTotalPriceOfBasket(basketId));

    }

}
