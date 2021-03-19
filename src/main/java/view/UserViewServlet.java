package view;

import com.google.gson.Gson;
import controller.AccountController;
import controller.EventController;
import controller.FileController;
import controller.UserController;
import model.Event;
import model.File;
import model.FileStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserViewServlet extends HttpServlet {
    private UserController controller;
    private FileController fileController;
    private EventController eventController;
    private AccountController accountController;

    @Override
    public void init() throws ServletException {
        controller = new UserController();
        fileController = new FileController();
        eventController = new EventController();
        accountController = new AccountController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("users", controller.getAll());
            resp.setContentType("application/json");
            controller.getAll().stream().map(s -> {String json = new Gson().toJson(s);
                return json;}).collect(Collectors.toList()).forEach((s) -> {
                try {
                    resp.getWriter().write(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> val = req.getReader().lines().collect(Collectors.toList());
            for (String stringTXT: val) {
                Map<String, String> result = new Gson().fromJson(stringTXT, Map.class);
                String name = result.get("name");
                String account_id = result.get("account_id");
                String files = result.get("files");
                controller.create(name, accountController.getByID(account_id), getFiles(files));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    private List<File> getFiles(String val){
        List<File> files = new ArrayList<>();
        String[] massFile = val.split(",");
        Arrays.stream(massFile).forEach(s -> {
            try {
                files.add(fileController.getByID(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return  files;
    }

    private List<Event> getEvents(String val){
        List<Event> events = new ArrayList<>();
        String[] massFile = val.split(",");
        Arrays.stream(massFile).forEach(s -> {
            try {
                events.add(eventController.getByID(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return  events;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> val = req.getReader().lines().collect(Collectors.toList());
            for (String stringTXT: val) {
                Map<String, String> result = new Gson().fromJson(stringTXT, Map.class);
                String id = result.get("id");
                String name = result.get("name");
                String account = result.get("account_id");
                String files = result.get("files");
                String events = result.get("events");
                controller.update(id,name, accountController.getByID(account),getFiles(files), getEvents(events));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        doGet(req, resp);
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> val = req.getReader().lines().collect(Collectors.toList());
            for (String stringTXT: val) {
                Map<String, String> result = new Gson().fromJson(stringTXT, Map.class);
                String id = result.get("id");
                controller.delete(id);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        doGet(req, resp);
    }
}
