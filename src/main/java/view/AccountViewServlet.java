package view;

import com.google.gson.Gson;
import controller.AccountController;
import model.AccountStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountViewServlet extends HttpServlet{
    private AccountController controller;

    @Override
    public void init() throws ServletException {
        controller = new AccountController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("accounts", controller.getAll());
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
        req.setCharacterEncoding("UTF-8");

        try {
            List<String> val = req.getReader().lines().collect(Collectors.toList());
            for (String stringTXT: val) {
                Map<String, String> result = new Gson().fromJson(stringTXT, Map.class);
                String name = result.get("name");
                controller.create(name);
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
                String accountStatus = result.get("accountStatus");
                controller.update(id,name, AccountStatus.valueOf(accountStatus));
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
