package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class ProductCategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public ProductCategoryRepository() {
    }

    @Transactional
    public void insert(ProductCategory productCategory){
        em.persist(productCategory);
    }

    @Transactional
    public void update(ProductCategory productCategory){
        em.merge(productCategory);
    }

    @Transactional
    public void delete(long id) {
        ProductCategory productCategory = em.find(ProductCategory.class, id);
        if(productCategory !=null){
            em.remove(productCategory);
        }
    }

    public ProductCategory findById(long id) {
        return em.find(ProductCategory.class, id);
    }

    public List<ProductCategory> findAll() {
        return em.createQuery("from ProductCategory", ProductCategory.class).getResultList();
    }
}
