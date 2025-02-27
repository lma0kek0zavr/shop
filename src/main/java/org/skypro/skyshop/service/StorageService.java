package org.skypro.skyshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() { 
        productMap = new HashMap<>();
        articleMap = new HashMap<>();
        config();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productMap.values());
    }

    public List<Article> getArticles() {
        return new ArrayList<>(articleMap.values());
    }

    public List<Searchable> getAllContent() {
        return Stream.concat(
            productMap.values().stream(),
            articleMap.values().stream()
        ).collect(Collectors.toCollection(ArrayList::new)); 
    }

    private void config() {
        productMap.put(UUID.randomUUID(), new SimpleProduct("Bread", 25, UUID.randomUUID()));
        productMap.put(UUID.randomUUID(), new SimpleProduct("Milk", 100, UUID.randomUUID()));
        productMap.put(UUID.randomUUID(), new SimpleProduct("Cheese", 150, UUID.randomUUID()));
        productMap.put(UUID.randomUUID(), new SimpleProduct("Sausage", 200, UUID.randomUUID()));
        productMap.put(UUID.randomUUID(), new SimpleProduct("Potato", 50, UUID.randomUUID()));

        articleMap.put(UUID.randomUUID(), new Article("BreadArticle", "Прекрасный хлеб, свежайщий", UUID.randomUUID()));
        articleMap.put(UUID.randomUUID(), new Article("MilkArticle", "Молоко, просто молоко", UUID.randomUUID()));
        articleMap.put(UUID.randomUUID(), new Article("CheeseArticle", "Тот самый пармезан", UUID.randomUUID()));
        articleMap.put(UUID.randomUUID(), new Article("SausageArticle", "Любимая докторская", UUID.randomUUID()));
        articleMap.put(UUID.randomUUID(), new Article("PotatoArticle", "Идеальная картошка", UUID.randomUUID()));  
    }
}
