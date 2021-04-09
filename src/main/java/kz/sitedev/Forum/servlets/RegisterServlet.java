package kz.sitedev.Forum.servlets;

import kz.sitedev.Forum.DBConnection;
import kz.sitedev.Forum.models.User;
import kz.sitedev.Forum.services.AuthService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user != null){
            resp.sendRedirect(req.getContextPath()+"/Hello");
            return;
        }
        try {
            req.getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("insert into users(name, email, password) values(?, ?, ?)");

            st.setString(1, req.getParameter("name"));
            String pwd = BCrypt.hashpw(req.getParameter("password"), BCrypt.gensalt());
            st.setString(2, req.getParameter("email"));
            st.setString(3, pwd);
            st.executeUpdate();

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            }
        User user = AuthService.getUser(req.getParameter("email"), req.getParameter("password"));

        HttpSession session = ((HttpServletRequest) req).getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(300);
        resp.sendRedirect(req.getContextPath()+"/Hello");
    }
}
