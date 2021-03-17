package view;

import com.google.gson.Gson;
import controller.EventController;
import controller.FileController;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class EventViewServlet extends HttpServlet {
    private EventController controller;
    private FileController fileController;

    @Override
    public void init() throws ServletException {
        controller = new EventController();
        fileController = new FileController();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("events", controller.getAll());
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
                String value = result.get("date");
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                Date date =format.parse(value);
                String file_id = result.get("file_id");
                controller.create(date, fileController.getByID(file_id));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<String> val = req.getReader().lines().collect(Collectors.toList());
            for (String stringTXT: val) {
                Map<String, String> result = new Gson().fromJson(stringTXT, Map.class);
                String id = result.get("id");
                String file_id = result.get("file_id");
                String value = result.get("date");
                SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
                Date date =format.parse(value);
                controller.update(id, date, fileController.getByID(file_id));
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
