package main.java.DAO;

import main.java.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import main.java.util.DBHelper;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private SessionFactory sessionFactory;

    public UserHibernateDAO() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User").list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addUser(String name, String soName, String email) {
        User user = new User(name, soName, email);
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(Long id, String name, String surname, String email) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE User SET name =:name, surname =:surname, email =:email WHERE id =:id");
            query.setParameter("id", id);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            query.setParameter("email", email);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where id = :id");
            query.setParameter("id", id);
            return (User) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}