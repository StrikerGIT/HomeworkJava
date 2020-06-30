package service;

import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class CartService implements Serializable {

    private List<ProductRepr> products = new ArrayList<>();

    public List<ProductRepr> getAll() {
        return products;
    }

    public void add(ProductRepr product) {
        products.add(product);
    }

}
