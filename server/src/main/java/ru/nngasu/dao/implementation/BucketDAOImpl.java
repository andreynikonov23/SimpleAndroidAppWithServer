package ru.nngasu.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.Bucket;

import java.util.List;

@Service
public class BucketDAOImpl implements DAO<Bucket, Integer> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Bucket> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Bucket").list();
        }
    }

    @Override
    public Bucket findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Bucket.class, id);
        }
    }

    @Override
    public void create(Bucket bucket) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(bucket);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Bucket bucket) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.refresh(bucket);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Bucket bucket) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.remove(bucket);
            session.getTransaction().commit();
        }
    }
}
