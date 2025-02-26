package org.skypro.skyshop.model.article;

import java.util.Objects;
import java.util.UUID;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchableComparator;

public final class Article implements Searchable, Comparable<Searchable> {
    private final String articleName;
    private final String articleText;
    private SearchableComparator comparator;
    private final UUID id;

    public Article(String articleName, String articleText, UUID id) {
        this.id = id;
        this.articleName = articleName;
        this.articleText = articleText;
        comparator = new SearchableComparator();
    }

    public String getArticleName() {
        return articleName;
    }

    public String getArticleText() {
        return articleText;
    }

    @Override
    public String toString() {
        return articleName + " " + articleText;
    }

    @Override
    public String searchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        return articleName == article.getArticleName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName);
    }

    @Override
    public int compareTo(Searchable o) {
        return comparator.compare(this, o);
    }

    @Override
    public UUID getId() {
        return id;
    }
}
