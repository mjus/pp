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

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    UserService service = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null || action.contains("auth")) {
            List<User> users = service.getAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        } else if (action.contains("back")) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (action.contains("update")) {
            User user = service.getUserById(Long.parseLong(req.getParameter("id")));
            req.setAttribute("user", user);
            req.getRequestDispatcher("update.jsp").forward(req, resp);
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action.contains("update")) {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            service.updateUser(Long.parseLong(id), name, surname, email);
        } else if (action.contains("delete")) {
            service.deleteUser(Long.parseLong(req.getParameter("id")));
        } else if (action.contains("add")) {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            service.addUser(name, surname, email);
        }
        resp.sendRedirect("/admin");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}