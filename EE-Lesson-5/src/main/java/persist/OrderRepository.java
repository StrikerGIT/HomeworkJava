package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@ApplicationScoped
@Named
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    @PostConstruct
    public void init() {
    }

    public OrderRepository() {
    }

    @Transactional
    public void insert(Order order){
        em.persist(order);
    }

    @Transactional
    public void update(Order order){
        em.merge(order);
    }

    @Transactional
    public void delete(long id) {
        Order order = em.find(Order.class, id);
        if(order!=null){
            em.remove(order);
        }
    }

    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("from Order", Order.class).getResultList();
    }
}
