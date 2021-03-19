package controller;

import model.Account;
import model.Event;
import model.File;
import model.User;
import repository.UserRepository;
import repository.hibernate.HibernateUserRepository;

import java.util.List;

public class UserController {
    private UserRepository repo = new HibernateUserRepository();


    public User create(String val, Account account, List<File> files) throws Exception {
        User user = new User();
        user.setName(val);
        user.setAccount(account);
        user.setFiles(files);
        return repo.save(user);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public User getByID(String val) throws Exception {
        User user = repo.getByID(Long.parseLong(val));
        if (user == null) {
            System.out.println("Введено не корректное значение");
        }
        return user;
    }

    public List<User> getAll() throws Exception {
        return repo.getAll();
    }

    public User update(String val1, String val2, Account account, List<File> files, List<Event> events) throws Exception {
        User user = new User();
        user.setId(Long.parseLong(val1));
        user.setName(val2);
        user.setAccount(account);
        user.setFiles(files);
        user.setEvents(events);
        return repo.update(user);
    }
}
