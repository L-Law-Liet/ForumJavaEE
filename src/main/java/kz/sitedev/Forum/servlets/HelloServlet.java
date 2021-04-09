package kz.sitedev.Forum.servlets;

import javafx.geometry.Pos;
import kz.sitedev.Forum.DBConnection;
import kz.sitedev.Forum.models.Comment;
import kz.sitedev.Forum.models.Post;
import kz.sitedev.Forum.models.User;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/Hello")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection con = null;
        ResultSet resultSet = null;
        List<Post> posts = new ArrayList<>();
        try {
            con = DBConnection.init();
            PreparedStatement st = con.prepareStatement("select * from posts p join users u on p.user_id=u.id");

            resultSet = st.executeQuery();
            if (resultSet != null){
                while(true){
                    ResultSet resultSet1 = null;
                    try {
                        if (!resultSet.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        Post post = new Post();
                        post.setId(resultSet.getInt("p.id"));
                        post.setTitle(resultSet.getString("p.title"));
                        post.setBody(resultSet.getString("p.body"));
                        post.setLikes(resultSet.getInt("p.likes"));
                        post.setDislikes(resultSet.getInt("p.dislikes"));

                        User user = new User();
                        user.setId(resultSet.getInt("u.id"));
                        user.setName(resultSet.getString("u.name"));
                        user.setEmail(resultSet.getString("u.email"));
                        post.setUser(user);

                        List<Comment> comments = new ArrayList<>();

                        PreparedStatement st1 = con.prepareStatement("select * from comments c join users u on c.user_id = u.id where post_id = ?");
                        st1.setInt(1, post.getId());
                        resultSet1 = st1.executeQuery();
                        if (resultSet1 != null){
                            while(true){
                                try {
                                    if (!resultSet1.next()) break;
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                try {
                                    Comment comment = new Comment();
                                    comment.setId(resultSet1.getInt("c.id"));
                                    comment.setBody(resultSet1.getString("c.body"));
                                    comment.setLikes(resultSet1.getInt("c.likes"));
                                    comment.setDislikes(resultSet1.getInt("c.dislikes"));

                                    User user1 = new User();
                                    user1.setId(resultSet.getInt("u.id"));
                                    user1.setName(resultSet.getString("u.name"));
                                    user1.setEmail(resultSet.getString("u.email"));

                                    comment.setUser(user1);
                                    comments.add(comment);
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                            }
                        }
                        st1.close();
                        post.setComments(comments);
                        posts.add(post);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            st.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("posts", posts);
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}