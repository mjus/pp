package main.java.servlet.auth;

import main.java.model.User;
import main.java.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        UserService us = UserService.getInstance();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login.isEmpty() || password.isEmpty() || !us.isUserExist(login, password)) {
            resp.sendRedirect("auth");
            return;
        }

        if(us.getRoleByLoginAndPassword(login, password).equals("admin")){
            filterChain.doFilter(req, resp);
            return;
        }
//
//        if (us.getRoleByLoginAndPassword(login, password).equals("user")){
//            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
//        }
    }

    @Override
    public void destroy() {

    }
}