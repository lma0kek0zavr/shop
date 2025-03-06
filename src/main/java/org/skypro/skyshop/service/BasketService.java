package org.skypro.skyshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void add(UUID id) { 
        Optional<Product> isExist = storageService.getProductByID(id);

        if (!isExist.isPresent()) { 
            throw new IllegalArgumentException("Товара не существует");
        }

        basket.add(id);
    }

    public UserBasket getUserBasket() {
        Map<UUID, Integer> products = new HashMap<>(basket.get());

        return new UserBasket(
            products.entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProductByID(entry.getKey()).get(), entry.getValue()))
                .collect(Collectors.toCollection(ArrayList::new))
        );
    }
}
