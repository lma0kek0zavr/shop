package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{
    private static final int FIXED_PRICE = 100;

    public FixPriceProduct(String productName, UUID id) {
        super(productName, id);
    }

    @Override
    public int getProductPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() { 
        return super.getProductName() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
