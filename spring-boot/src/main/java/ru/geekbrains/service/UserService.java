package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.ProductSpecification;
import ru.geekbrains.persist.repo.UserRepository;
import ru.geekbrains.persist.repo.UserSpecification;

import javax.swing.text.html.HTMLDocument;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> filterByAge(Integer minAge, Integer maxAge, String UserName, Pageable pegeable) {
        Specification <User> specification = UserSpecification.trueLiteral();
        if (minAge != null){
            specification = specification.and(UserSpecification.ageGreaterThanOrEqual(minAge));
        }
        if (maxAge != null){
            specification = specification.and(UserSpecification.ageLessThanOrEqual(maxAge));
        }
        if (UserName != null && !UserName.isEmpty()){
            specification = specification.and(UserSpecification.findByUserName(UserName));
        }
        return repository.findAll(specification, pegeable);
    }

    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
