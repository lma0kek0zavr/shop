package org.skypro.skyshop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BasketServiceTest {
    @InjectMocks ProductBasket productBasketMock;
    @InjectMocks StorageService storageServiceMock;
    @Mock BasketService basketServiceMock;

    @BeforeEach
    public void setUp() { 
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenProductIsNotExist_ThenThrowException() { 
        doThrow(new NoSuchProductException()).when(basketServiceMock).add(Mockito.any(UUID.class));

        assertThrows(NoSuchProductException.class, () -> basketServiceMock.add(UUID.randomUUID()));
    }

    @Test
    void whenProductIsExist_ThenAddProductToBasket() { 
        Product product = new SimpleProduct("Bread", 25, UUID.randomUUID());

        doNothing().when(basketServiceMock).add(Mockito.any(UUID.class));

        basketServiceMock.add(product.getId());

        verify(basketServiceMock, atLeastOnce()).add(product.getId());
    }

    @Test
    void whenProductBasketIsEmpty_ThenReturnEmptyBasket() { 
        when(basketServiceMock.getUserBasket()).thenReturn(new UserBasket(List.of()));

        UserBasket products = basketServiceMock.getUserBasket();

        assertEquals(0, products.getItems().size());
    }

    @Test
    void whenProductBasketIsNotEmpty_ThenReturnNotEmptyBasket() { 
        Product product = new SimpleProduct("Bread", 25, UUID.randomUUID());
        Product product2 = new SimpleProduct("Milk", 100, UUID.randomUUID());
        Product product3 = new SimpleProduct("Cheese", 150, UUID.randomUUID());

        when(basketServiceMock.getUserBasket()).thenReturn(new UserBasket(List.of(
            new BasketItem(product, 1),
            new BasketItem(product2, 2),
            new BasketItem(product3, 3)
        )));

        UserBasket products = basketServiceMock.getUserBasket();

        assertEquals(3, products.getItems().size());
    }
}
