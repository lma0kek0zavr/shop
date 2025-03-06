package org.skypro.skyshop.model.basket;

import java.util.List;

public final class UserBasket {
    private List<BasketItem> items;
    private int totalPrice = 0;

    public UserBasket(List<BasketItem> items) { 
        this.items = items;
        this.totalPrice = calcTotalPrice();
    }

    private int calcTotalPrice() { 
        return items.stream()
            .map(i -> i.getPrice() * i.getQuantity())
            .reduce(0, Integer::sum);
    }

    public List<BasketItem> getItems() { return items; }

    public int getTotalPrice() { return totalPrice; }
}
