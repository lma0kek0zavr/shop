package org.skypro.skyshop.service;

import java.util.List;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public SearchResult search(String pattern) {
        List<Searchable> content = storageService.getAllContent();
        
        return content.stream()
            .filter(s -> s.getSearchTerm(s.searchTerm(), pattern) > 0)
            .map(SearchResult::fromSearchable)
            .findFirst()
            .orElse(null);
    }       
}
