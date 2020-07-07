package service;

import persist.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductServiceLocal {

    void insert(ProductRepr productRepr);

    void update(ProductRepr productRepr);

    void delete(long id);

    ProductRepr findById(long id);

    List<ProductRepr> findAll();
}
