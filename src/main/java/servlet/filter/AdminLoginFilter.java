package main.java.servlet.filter;

import main.java.model.User;
import main.java.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/admin/*")
public class AdminLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();

        if (nonNull(session.getAttribute("role")) && session.getAttribute("role").equals("admin")) {
            filterChain.doFilter(req, resp);
        } else {
            session.removeAttribute("login");
            session.removeAttribute("password");
            session.removeAttribute("role");
            resp.sendRedirect("/");
        }
    }
}