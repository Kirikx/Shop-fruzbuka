package ru.fruzbuka.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.service.dto.LineItem;
import ru.fruzbuka.service.impl.CartServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CartServiceTest {

    private CartService cartService;

    @BeforeEach
    public void init() {
        cartService = new CartServiceImpl();
    }

    @Test
    public void testEmtyCart() {
        assertEquals(0, cartService.getLineItems().size());
        assertEquals(BigDecimal.ZERO, cartService.getSubTotal());
    }

    @Test
    public void testAddOneProduct() {
        ProductRepr expectedProduct = new ProductRepr();
        expectedProduct.setId(1L);
        expectedProduct.setPrice(new BigDecimal("123"));
        expectedProduct.setName("Apple");

        cartService.addProductQty(expectedProduct, "red", "organic", 1);

        List<LineItem> lineItems = cartService.getLineItems();

        assertNotNull(lineItems);
        assertEquals(1, lineItems.size());

        LineItem lineItem = lineItems.get(0);
        assertEquals("red", lineItem.getColor());
        assertEquals("organic", lineItem.getMaterial());
        assertEquals(1, lineItem.getQty());

        assertEquals(expectedProduct.getId(), lineItem.getProductId());
        assertNotNull(lineItem.getProductRepr());
        assertEquals(expectedProduct.getName(), lineItem.getProductRepr().getName());

    }

}