package ru.nngasu.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nngasu.security.SecurityUser;
import ru.nngasu.dao.DAO;
import ru.nngasu.models.User;

import java.util.List;

@Service
public class UserService implements DAO<User, Integer>, UserDetailsService {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> showAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User").list();
        }
    }

    @Override
    public User findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(User.class, id);
        }
    }

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.refresh(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    public User findByUsername(String username) {
        User user = null;
        try(Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", username);
            user = query.getSingleResultOrNull();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return SecurityUser.fromUser(findByUsername(username));
    }
}
