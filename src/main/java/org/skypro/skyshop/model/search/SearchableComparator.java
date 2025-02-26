package org.skypro.skyshop.model.search;

import java.util.Comparator;

public class SearchableComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable s1, Searchable s2) {
        Integer o1 = s1.searchTerm().length();
        Integer o2 = s2.searchTerm().length();

        if (Integer.compare(o1, o2) == 0) {
            return Integer.compare(o1, o2);
        }

        return o2.compareTo(o1);
    }
    
}
