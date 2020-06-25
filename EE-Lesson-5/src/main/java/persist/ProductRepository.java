package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.*;
import java.util.List;

@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    @PostConstruct
    public void init() {
            if (this.findAll().isEmpty()) {
                try {
                    ut.begin();
                    this.insert(new Product(-1L, "Product1", 100));
                    this.insert(new Product(-1L, "Product2", 200));
                    this.insert(new Product(-1L, "Product3", 300));
                    this.insert(new Product(-1L, "Product4", 300));
                    this.insert(new Product(-1L, "Product5", 300));
                    this.insert(new Product(-1L, "Product6", 300));
                    this.insert(new Product(-1L, "Product7", 300));
                    this.insert(new Product(-1L, "Product8", 300));
                    this.insert(new Product(-1L, "Product9", 300));
                    ut.commit();
                } catch (Exception e) {
                    try {
                        ut.rollback();
                    } catch (SystemException ex) {
                        logger.error("",ex);
                    }
                    e.printStackTrace();
                }
            }
    }

    public ProductRepository() {
    }

    @Transactional
    public void insert(Product product){
        em.persist(product);
    }

    @Transactional
    public void update(Product product){
        em.merge(product);
    }

    @Transactional
    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if(product!=null){
            em.remove(product);
        }
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

}
