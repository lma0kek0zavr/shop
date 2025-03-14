package org.skypro.skyshop.model.search;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.skypro.skyshop.util.BestResultNotFoundException;

public class SearchEngine {
    private Set<Searchable> searchables;

    public SearchEngine() { 
        searchables = new HashSet<>();
    }

    public Set<Searchable> search(String... query) { 
        return searchables.stream()
            .filter(s -> Arrays.stream(query).anyMatch(q -> s.getSearchTerm(s.searchTerm(), q) > 0))
            .collect(Collectors.toCollection(TreeSet::new));
    }

    public boolean add(Searchable searchable) { 
        return searchables.add(searchable);
    }

    public Searchable findMostSuitableObject(String search) throws BestResultNotFoundException {
        Searchable mostSuitable = null;
      
        mostSuitable = searchables.stream()
            .filter(s -> s.getSearchTerm(s.searchTerm(), search) > 0)
            .findFirst()
            .orElseThrow(() -> new BestResultNotFoundException("Ничего не найдено"));

        return mostSuitable;
    }
}
