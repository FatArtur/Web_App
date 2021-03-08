package view;

import controller.AccountController;
import model.AccountStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountViewServlet extends HttpServlet {
    private AccountController controller;

    @Override
    public void init() throws ServletException {
        controller = new AccountController();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("accounts", controller.getAll());
        } catch (Exception e) {
            System.out.println(e);
        }
        req.getRequestDispatcher("/account.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Enter doPost");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        try {
            if (action.equals("create")) {
                String name = req.getParameter("name");
                controller.create(name);
            }

            if (action.equals("update")){
                String id = req.getParameter("id");
                String name = req.getParameter("name");
                String accountStatus = req.getParameter("accountStatus");
                controller.update(id,name, AccountStatus.valueOf(accountStatus));
            }

            if (action.equals("delete")){
                String id = req.getParameter("id");
                controller.delete(id);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        doGet(req, resp);
    }

}
