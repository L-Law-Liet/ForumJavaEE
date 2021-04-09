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

@WebServlet(value = "/UpdateComment")
public class UpdateCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("update comments set body = ? where id = ?");

            st.setString(1, request.getParameter("body"));
            st.setString(2, request.getParameter("id"));
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
