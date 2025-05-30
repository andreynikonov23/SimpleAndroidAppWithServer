package ru.nngasu.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.Order;

import java.util.List;

@Service
public class OrderDAOImpl implements DAO<Order, Integer> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order").list();
        }
    }

    @Override
    public Order findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Order.class, id);
        }
    }

    @Override
    public void create(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Order order) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.refresh(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Order order) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.remove(order);
            session.getTransaction().commit();
        }
    }
}
