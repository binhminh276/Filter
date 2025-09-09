package filter.Controllers;

import java.io.IOException;

import filter.Entity.Users;
import filter.Services.UserService;
import filter.Services.Impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Forward đến trang login.jsp
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Users user = userService.login(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);

            // redirect theo role
            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/user/home");
            } else if (user.getRoleid() == 2) {
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            } else if (user.getRoleid() == 3) {
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            }
        } else {
            req.setAttribute("error", "Sai username hoặc password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}

