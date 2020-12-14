package ru.fruzbuka.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.assertj.core.util.Lists;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.fruzbuka.controller.repr.ProductRepr;
import ru.fruzbuka.persist.entity.Brand;
import ru.fruzbuka.persist.entity.Category;
import ru.fruzbuka.persist.entity.Product;
import ru.fruzbuka.persist.repo.BrandRepository;
import ru.fruzbuka.persist.repo.CategoryRepository;
import ru.fruzbuka.persist.repo.ProductRepository;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @MockBean
    private EurekaClient eurekaClient;

    @BeforeEach
    public void init() {
        InstanceInfo instanceInfo = mock(InstanceInfo.class);
        when(instanceInfo.getHomePageUrl())
                .thenReturn("mock-homepage-url");
        when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean()))
                .thenReturn(instanceInfo);
    }

    @Test
    public void testProductDetails() throws Exception {
        Category category = categoryRepository.save(new Category("Vegan"));
        Brand brand = brandRepository.save(new Brand("Apple"));
        Product product = productRepository.save(new Product("Apple", new BigDecimal("123"), brand, Lists.newArrayList(category)));

        mockMvc.perform(get("/product/" + product.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("shop-details"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("brands"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("product", new BaseMatcher<Product>() {

                    @Override
                    public void describeTo(Description description) {

                    }

                    @Override
                    public boolean matches(Object o) {
                        if (o instanceof ProductRepr) {
                            ProductRepr productRepr = (ProductRepr) o;
                            return productRepr.getId().equals(product.getId());
                        }
                        return false;
                    }
                }));
    }

}