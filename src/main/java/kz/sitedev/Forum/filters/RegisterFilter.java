package kz.sitedev.Forum.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/Register")
public class RegisterFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getMethod().equalsIgnoreCase("post")){
            List<String> errors = new ArrayList<>();
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String password_confirmation = req.getParameter("password_confirmation");
            if (name.trim().equals("")){
                errors.add("Name is required");
            }
            if (email.trim().equals("")){
                errors.add("Email is required");
            }
            if (password.trim().equals("")){
                errors.add("Password is required");
            }
            if (!password.equals(password_confirmation)){
                errors.add("Passwords are not equal");
            }
            if (!errors.isEmpty()){
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/register.jsp").include(req, resp);
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
