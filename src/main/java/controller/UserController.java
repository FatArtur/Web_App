package controller;

import model.Account;
import model.Event;
import model.File;
import model.User;
import repository.AccountRepository;
import repository.EventRepository;
import repository.FileRepository;
import repository.UserRepository;
import repository.hibernate.HibernateAccountRepository;
import repository.hibernate.HibernateEventRepository;
import repository.hibernate.HibernateFileRepository;
import repository.hibernate.HibernateUserRepository;

import java.util.List;

public class UserController {
    private UserRepository repo = new HibernateUserRepository();

    private AccountRepository createAccountRep(){
        return new HibernateAccountRepository();
    }

    private EventRepository createEventRep(){
        return new HibernateEventRepository();
    }

    private FileRepository createFileRep(){
        return new HibernateFileRepository();
    }

    public List<Event> getEvents() throws Exception{
        EventRepository eventRepository = createEventRep();
        return eventRepository.getAll();
    }

    public Event giveEvent(String s) throws Exception{
        EventRepository eventRepository = createEventRep();
        return eventRepository.getByID(Long.parseLong(s));
    }

    public List<File> getFiles() throws Exception{
        FileRepository fileRepository = createFileRep();
        return fileRepository.getAll();
    }

    public File giveFile(String s) throws Exception{
        FileRepository fileRepository = createFileRep();
        return fileRepository.getByID(Long.parseLong(s));
    }


    public User create(String val, Account account, List<File> files, List<Event> events) throws Exception {
        User user = new User();
        user.setName(val);
        user.setAccount(account);
        user.setFiles(files);
        user.setEvents(events);
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
