package dao;

import entity.Order;
import entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

@Component
public class OrderDAO {

    private SessionFactory factory;
    private EntityManager em;

    protected OrderDAO() {
        this.factory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        em = factory.createEntityManager();
    }

    public List<Order> getAll() {
        em.getTransaction().begin();
        List<Order> orders = em.createQuery("FROM entity.Order", Order.class).getResultList();
        em.getTransaction().commit();
        return orders;
    }

    public void create(Order order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public List<Order> getOrdersPeriod(LocalDate dateFrom, LocalDate dateTo) {
        em.getTransaction().begin();
        List<Order> orderList = em.createQuery("SELECT o FROM entity.Order o "
                + "WHERE o.dateOrder >= :dateFrom AND o.dateOrder <= :dateTo" +
                " ORDER by o.dateOrder", Order.class)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .getResultList();
        if (orderList.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return orderList;
    }

    public List<Order> getOrdersPeriodUser(LocalDate dateFrom, LocalDate dateTo, User user) {
        em.getTransaction().begin();
        List<Order> orderList = em.createQuery("SELECT o FROM entity.Order o "
                + "WHERE o.dateOrder >= :dateFrom AND o.dateOrder <= :dateTo AND o.user = :user" +
                " ORDER by o.dateOrder", Order.class)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo)
                .setParameter("user", user)
                .getResultList();
        if (orderList.isEmpty()) {
            em.getTransaction().commit();
            return null;
        }
        em.getTransaction().commit();
        return orderList;
    }

}
