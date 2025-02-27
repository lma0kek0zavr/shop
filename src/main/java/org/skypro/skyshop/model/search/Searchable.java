package org.skypro.skyshop.model.search;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Searchable {
    
    String searchTerm();

    String getContentType();

    UUID getId();

    @JsonIgnore
    default String getStringRepresentation() {
        return searchTerm() + "\n" + getContentType();
    }    

    default int getSearchTerm(String searchTerm, String subString) {
        int count = 0;
        String str = searchTerm.toLowerCase();
        String sub = subString.toLowerCase();

        while (str.contains(sub)) {
            str = str.substring(str.indexOf(sub) + sub.length());
            count++;
        }
        return count;
    }
}
