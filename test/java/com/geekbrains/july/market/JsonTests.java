package com.geekbrains.july.market;

import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Role> jacksonRole;

    @Autowired
    private JacksonTester<Product> jacksonProduct;


    @Test
    public void jsonSerializationTestRole() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        // {
        //   "id": 1,
        //   "name": "USER"
        // }
        assertThat(this.jacksonRole.write(role)).hasJsonPathNumberValue("$.id");
        assertThat(this.jacksonRole.write(role)).extractingJsonPathStringValue("$.name").isEqualTo("USER");
    }

    @Test
    public void jsonDeserializationTestRole() throws Exception {
        String content = "{\"id\": 2,\"name\":\"ADMIN\"}";
        Role realRole = new Role();
        realRole.setId(2L);
        realRole.setName("ADMIN");

        assertThat(this.jacksonRole.parse(content)).isEqualTo(realRole);
        assertThat(this.jacksonRole.parseObject(content).getName()).isEqualTo("ADMIN");
    }

    @Test
    public void jsonSerializationTestProduct() throws Exception {
        Product product = new Product(9999L,"TestProduct",new BigDecimal(1000));
        // {
        //   "id": 9999,
        //   "title": "TestProduct"
        //   "price": 1000
        // }
        assertThat(this.jacksonProduct.write(product)).hasJsonPathNumberValue("$.id");
        assertThat(this.jacksonProduct.write(product)).extractingJsonPathStringValue("$.title").isEqualTo("TestProduct");
        assertThat(this.jacksonProduct.write(product)).extractingJsonPathNumberValue("$.price").isEqualTo(1000);
    }

    @Test
    public void jsonDeserializationTestProduct() throws Exception {
        String content = "{\"id\": 9999,\"title\":\"TestProduct\",\"price\": 1000\"}";
        Product product = new Product(9999L,"TestProduct",new BigDecimal(1000));

        assertThat(this.jacksonProduct.parse(content)).isEqualTo(product);
        assertThat(this.jacksonProduct.parseObject(content).getTitle()).isEqualTo("TestProduct");
    }

}