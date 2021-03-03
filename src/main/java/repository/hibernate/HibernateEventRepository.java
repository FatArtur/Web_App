package repository.hibernate;

import model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.EventRepository;

import java.util.List;

public class HibernateEventRepository implements EventRepository {
    @Override
    public Event save(Event val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(Event.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Event event = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(event);
        transaction.commit();
        session.close();
    }

    @Override
    public Event getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Event event = session.get(Event.class, id);
        session.close();
        return event;
    }

    @Override
    public List<Event> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Event> list = session.createQuery("From Event").list();
        session.close();
        return list;
    }

    @Override
    public Event update(Event val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
