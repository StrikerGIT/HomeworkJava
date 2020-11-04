package com.geekbrains.l_7.repositories.specifications;

import com.geekbrains.l_7.entities.Student;
import org.springframework.data.jpa.domain.Specification;

public class StudentSpecifications {
    public static Specification<Student> priceGreaterOrEqualsThan(int minAge) {
        return (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge);
    }

    public static Specification<Student> priceLesserOrEqualsThan(int maxAge) {
        return (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("age"), maxAge);
    }

    public static Specification<Student> titleLike(String name) {
        return (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), String.format("%%%s%%", name));
    }
}
