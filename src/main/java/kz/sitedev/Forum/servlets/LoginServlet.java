package kz.sitedev.Forum.servlets;

import kz.sitedev.Forum.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null){
            resp.sendRedirect(req.getContextPath()+"/Hello");
            return;
        }
        try {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        HttpSession session = req.getSession();
        session.setAttribute("email", email);
        session.setMaxInactiveInterval(300);
        resp.sendRedirect(req.getContextPath()+"/Hello");
    }
}
