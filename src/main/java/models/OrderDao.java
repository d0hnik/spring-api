package models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class OrderDao {

    @PersistenceContext
    private EntityManager em;

    public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
        return order;
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public Order findById(Long id) {
        TypedQuery<Order> query = em.createQuery("select o from Order o where o.id = :id", Order.class);
        query.setParameter("id", id);
        return query.getResultStream().findFirst().orElse(null);
    }

    public void deleteById(Long orderId) {
        throw new RuntimeException();
    }
}