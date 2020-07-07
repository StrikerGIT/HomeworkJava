package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ProductRepr;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public ProductRepository() {
    }

    public void insert(Product product){
        em.persist(product);
    }

    public void update(Product product){
        em.merge(product);
    }

    public void delete(long id) {
        Product product = em.find(Product.class, id);
        if(product!=null){
            em.remove(product);
        }
    }

    public Product findById(long id) {
        return em.find(Product.class, id);
    }

    public ProductRepr findProductReprById(long id) {
        return em.createQuery("select new service.ProductRepr(p.id, p.title, p.coast, p.productCategory.id, p.productCategory.name) from Product p where p.id = :id", ProductRepr.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

    public List<ProductRepr> findAllProductRepr() {
        return em.createQuery("select new service.ProductRepr(p.id, p.title, p.coast, p.productCategory.id, p.productCategory.name) from Product p", ProductRepr.class)
                .getResultList();
    }

}
