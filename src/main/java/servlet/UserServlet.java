package main.java.servlet;

import main.java.model.User;
import main.java.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class UserServlet extends HttpServlet {
    UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            List<User> users = service.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.equals("update")) {
            User user = service.getUserById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            service.deleteUser(Long.parseLong(req.getParameter("id")));
            resp.sendRedirect("/");
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action.equals("update")) {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            service.updateUser(Long.parseLong(id), name, surname, email);
        } else if (action.equals("add")) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            service.addUser(name, surname, email);
        }
        resp.sendRedirect("/");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}