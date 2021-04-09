package kz.sitedev.Forum.servlets;

import kz.sitedev.Forum.DBConnection;
import kz.sitedev.Forum.models.User;

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

@WebServlet(value = "/CreateComment")
public class CreateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("user");
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("insert into comments(body, user_id, post_id) values(?, ?, ?)");

            st.setString(1, request.getParameter("body"));
            st.setInt(3, auth.getId());
            st.setString(2, request.getParameter("post_id"));
            st.executeUpdate();

            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/Hello");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
