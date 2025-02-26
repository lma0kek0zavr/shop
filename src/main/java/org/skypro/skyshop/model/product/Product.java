package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchableComparator;

public abstract class Product implements Searchable, Comparable<Searchable> {
    protected String productName;
    private SearchableComparator comparator;
    protected final UUID id;

    public Product(String productName, UUID id) {
        if (productName.isBlank() || productName.isEmpty() || productName == null) {
            throw new IllegalArgumentException("Неправильное имя");
        } 

        this.productName = productName;
        this.id = id;

        comparator = new SearchableComparator();
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String searchTerm() {
        return productName;
    }

    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    public abstract int getProductPrice();

    public abstract boolean isSpecial();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return productName == product.getProductName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName);
    }

    @Override
    public int compareTo(Searchable o) {
        return comparator.compare(this, o);
    }

    public UUID getId() {
        return id;
    }
}
