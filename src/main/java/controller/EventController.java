package controller;

import model.Event;
import model.File;
import repository.EventRepository;
import repository.hibernate.HibernateEventRepository;

import java.util.Date;
import java.util.List;

public class EventController {
    private EventRepository repo = new HibernateEventRepository();

    public Event create(Date date, File file) throws Exception {
        Event event = new Event();
        event.setDate(date);
        event.setFile(file);
        return repo.save(event);
    }

    public void delete(String val) throws Exception {
        repo.deleteById(Long.parseLong(val));
    }

    public Event getByID(String val) throws Exception {
        Event event = repo.getByID(Long.parseLong(val));
        if (event == null) {
            System.out.println("Введено не корректное значение");
        }
        return event;
    }

    public List<Event> getAll() throws Exception {
        List<Event> list = repo.getAll();
        return list;
    }

    public Event update(String val1, Date date, File val2) throws Exception {
        Event event = new Event();
        event.setId(Long.parseLong(val1));
        event.setDate(date);
        event.setFile(val2);
        return repo.update(event);
    }
}
