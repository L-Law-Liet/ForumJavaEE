package kz.sitedev.Forum.servlets;

import kz.sitedev.Forum.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(value = "/UpdatePost")
public class UpdatePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        try {
            con = DBConnection.init();
            PreparedStatement st = con
                    .prepareStatement("update posts set title = ?, body = ? where id = ?");

            st.setString(1, request.getParameter("title"));
            st.setString(2, request.getParameter("body"));
            st.setString(3, request.getParameter("id"));
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
