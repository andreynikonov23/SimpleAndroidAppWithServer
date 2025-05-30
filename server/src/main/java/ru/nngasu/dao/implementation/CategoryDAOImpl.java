package ru.nngasu.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.Category;

import java.util.List;

@Service
public class CategoryDAOImpl implements DAO<Category, Integer> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Category").list();
        }
    }

    @Override
    public Category findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Category.class, id);
        }
    }

    @Override
    public void create(Category category) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Category category) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.refresh(category);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Category category) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.remove(category);
            session.getTransaction().commit();
        }
    }
}
