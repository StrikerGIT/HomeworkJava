package com.geekbrains.l_7.utils;

import com.geekbrains.l_7.entities.Student;
import com.geekbrains.l_7.repositories.specifications.StudentSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class StudentFilter {
    private Specification<Student> spec;
    private StringBuilder filterDefinition;

    public StudentFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_age") && !map.get("min_age").isEmpty()) {
            int minAge = Integer.parseInt(map.get("min_age"));
            spec = spec.and(StudentSpecifications.priceGreaterOrEqualsThan(minAge));
            filterDefinition.append("&min_age=").append(minAge);
        }
        if (map.containsKey("max_age") && !map.get("max_age").isEmpty()) {
            int maxAge = Integer.parseInt(map.get("max_age"));
            spec = spec.and(StudentSpecifications.priceLesserOrEqualsThan(maxAge));
            filterDefinition.append("&max_age=").append(maxAge);
        }
        if (map.containsKey("name") && !map.get("name").isEmpty()) {
            String name = map.get("name");
            spec = spec.and(StudentSpecifications.titleLike(name));
            filterDefinition.append("&name=").append(name);
        }
    }
}
