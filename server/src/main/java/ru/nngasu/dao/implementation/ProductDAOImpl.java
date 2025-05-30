package ru.nngasu.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.Product;

import java.util.List;

@Service
public class ProductDAOImpl implements DAO<Product, String> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product").list();
        }
    }

    @Override
    public Product findById(String art) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Product.class, art);
        }
    }

    @Override
    public void create(Product product) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Product product) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.refresh(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Product product) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.remove(product);
            session.getTransaction().commit();
        }
    }
}
