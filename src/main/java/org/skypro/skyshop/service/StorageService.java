package org.skypro.skyshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.exceptions.NoSuchProductException;
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

    public Optional<Product> getProductByID(UUID id) throws NoSuchProductException {
        return Optional.ofNullable(productMap.get(id));
    }

    private void config() {
        UUID[] keys = new UUID[10];
        keys = generateKeys(10);

        productMap.put(keys[0], new SimpleProduct("Bread", 25, keys[0]));
        productMap.put(keys[1], new SimpleProduct("Milk", 100, keys[1]));
        productMap.put(keys[2], new SimpleProduct("Cheese", 150, keys[2]));
        productMap.put(keys[3], new SimpleProduct("Sausage", 200, keys[3]));
        productMap.put(keys[4], new SimpleProduct("Potato", 50, keys[4]));

        articleMap.put(keys[5], new Article("BreadArticle", "Прекрасный хлеб, свежайщий", keys[5]));
        articleMap.put(keys[6], new Article("MilkArticle", "Молоко, просто молоко", keys[6]));
        articleMap.put(keys[7], new Article("CheeseArticle", "Тот самый пармезан", keys[7]));
        articleMap.put(keys[8], new Article("SausageArticle", "Любимая докторская", keys[8]));
        articleMap.put(keys[9], new Article("PotatoArticle", "Идеальная картошка", keys[9]));  
    }

    private UUID[] generateKeys(int quantity) { 
        UUID[] keys = new UUID[quantity];
        for (int i = 0; i < quantity; i++) { 
            keys[i] = UUID.randomUUID();
        }
        return keys;
    }
}
