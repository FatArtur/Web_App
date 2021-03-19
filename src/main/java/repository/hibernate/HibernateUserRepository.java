package repository.hibernate;

import model.File;
import model.FileStatus;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.UserRepository;

import java.util.List;

public class HibernateUserRepository implements UserRepository {
    @Override
    public User save(User val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(User.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        User user = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.createQuery("SELECT a FROM User a JOIN FETCH a.files WHERE a.id = " + id,
                User.class).getSingleResult();
        try {
            user = session.createQuery("SELECT a FROM User a JOIN FETCH a.events WHERE a.id = " + id,
                    User.class).getSingleResult();
        } catch (Exception e){}
//        user = session.createQuery("SELECT a FROM User a JOIN FETCH a.events WHERE a.id = " + id,
//                User.class).getSingleResult();
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = session.createQuery("SELECT DISTINCT a FROM User a left " +
                "join fetch a.files order by a.id").getResultList();
        list = session.createQuery("SELECT DISTINCT a FROM User a left " +
                "join fetch a.events order by a.id").getResultList();
        session.close();
        return list;
    }

    @Override
    public User update(User val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
