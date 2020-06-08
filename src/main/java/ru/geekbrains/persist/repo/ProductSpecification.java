package ru.geekbrains.persist.repo;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.entity.Product;

public class ProductSpecification {

    public static Specification<Product> trueLiteral (){
        return (root, query, builder) -> builder.isTrue(builder.literal(true));
    }

    public static Specification<Product> coastGreaterThanOrEqual (Integer coast){
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("coast"), coast);
    }

    public static Specification<Product> coastLessThanOrEqual (Integer coast){
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("coast"), coast);
    }

    public static Specification<Product> findByProductTitle (String title){
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }
}
