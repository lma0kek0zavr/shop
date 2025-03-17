package org.skypro.skyshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchServiceTest {
    @Mock StorageService storageServiceMock;
    @Mock SearchService searchServiceMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bread", "Milk", "Cheese"})
    void whenNoProduct_ThenReturnNull(String toSearch) {
        when(searchServiceMock.search(anyString())).thenReturn(null);

        SearchResult result = searchServiceMock.search(toSearch);

        assertEquals(null, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bread", "Milk", "Cheese"})
    void whenProduct_ThenReturnProduct(String toSearch) {
        when(searchServiceMock.search(anyString())).thenReturn(null);

        SearchResult result = searchServiceMock.search(toSearch);

        verify(searchServiceMock, times(1)).search(toSearch);

        assertEquals(null, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bread", "Milk", "Cheese"})
    void whenProduct_ThenReturnProductList(String toSearch) {
        Product product = new SimpleProduct("Bread", 25, UUID.randomUUID());
        Product product2 = new SimpleProduct("Milk", 100, UUID.randomUUID());
        Product product3 = new SimpleProduct("Cheese", 150, UUID.randomUUID());

        when(storageServiceMock.getAllContent()).thenReturn(List.of(product, product2, product3));
        when(searchServiceMock.search(toSearch)).thenReturn(SearchResult.fromSearchable(product));

        SearchResult result = searchServiceMock.search(toSearch);

        verify(searchServiceMock, times(1)).search(toSearch);

        assertEquals(product.getProductName(), result.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bred", "breeed", "BRead", "bReaD", "br"})
    void whenProductNameIsDifferent_ThenReturnMostValidProduct(String toSearch) {
        Product product = new SimpleProduct("Bread", 25, UUID.randomUUID());

        when(storageServiceMock.getAllContent()).thenReturn(List.of(product));
        when(searchServiceMock.search(toSearch)).thenReturn(SearchResult.fromSearchable(product));

        searchServiceMock.search(toSearch);

        Assertions.assertThat(product.searchTerm()).hasSizeGreaterThan(0); 
    }
}
