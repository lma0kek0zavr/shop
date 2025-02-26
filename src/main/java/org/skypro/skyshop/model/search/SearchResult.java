package org.skypro.skyshop.model.search;

import java.util.UUID;

public final class SearchResult {
    private UUID id;
    private String name;
    private String contentType;

    private SearchResult(UUID id, String name, String contentType) { 
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        return new SearchResult(searchable.getId(), 
                                searchable.searchTerm(), 
                                searchable.getContentType());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }
}