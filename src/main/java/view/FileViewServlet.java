package view;

import com.google.gson.Gson;
import controller.FileController;
import model.FileStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileViewServlet extends HttpServlet {
    private FileController controller;

    @Override
    public void init() throws ServletException {
        controller = new FileController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("files", controller.getAll());
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
                String address = result.get("address");
                controller.create(name, address);
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
                String name = result.get("name");
                String address = result.get("address");
                String fileStatus = result.get("fileStatus");
                controller.update(id,name, address, FileStatus.valueOf(fileStatus));
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
