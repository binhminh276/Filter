package filter.Controllers;
import java.io.IOException;

import filter.Entity.Users;
import filter.Services.CategoryService;
import filter.Services.Impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/user/home", "/manager/home", "/admin/home"})
public class HomeServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users user = (Users) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();

        if (uri.endsWith("/user/home")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
        } else if (uri.endsWith("/manager/home")) {
            req.setAttribute("categories", categoryService.findByUserId(user.getId()));
            req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
        } else if (uri.endsWith("/admin/home")) {
            req.setAttribute("categories", categoryService.findAll());
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
        }
    }
}