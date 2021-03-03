package repository.hibernate;

import model.File;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.FileRepository;

import java.util.List;

public class HibernateFileRepository implements FileRepository {
    @Override
    public File save(File val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long generatedId = (Long) session.save(val);
        transaction.commit();
        val = session.get(File.class, generatedId);
        session.close();
        return val;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        File file = getByID(id);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(file);
        transaction.commit();
        session.close();
    }

    @Override
    public File getByID(Long id) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        File file = session.get(File.class, id);
        session.close();
        return file;
    }

    @Override
    public List<File> getAll() throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<File> list = session.createQuery("From File").list();
        session.close();
        return list;
    }

    @Override
    public File update(File val) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(val);
        transaction.commit();
        session.close();
        return val;
    }
}
