package filter.Controllers;

import java.io.IOException;
import java.util.List;

import filter.Entity.Category;
import filter.Entity.Users;
import filter.Services.CategoryService;
import filter.Services.Impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/user/category", "/manager/category", "/admin/category",
    "/user/category/add", "/manager/category/add", "/admin/category/add",
    "/user/category/edit", "/manager/category/edit", "/admin/category/edit",
    "/user/category/delete", "/manager/category/delete", "/admin/category/delete"})
public class CategoryServlet  extends HttpServlet {
	
	private CategoryService categoryService = new CategoryServiceImpl();
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

		Users user = (Users) req.getSession().getAttribute("user");
	    String uri = req.getRequestURI();

	    if (uri.contains("category/add")) {
	        req.setAttribute("role", rolePath(user).replace("/", "")); // "admin" / "manager" / "user"
	        req.getRequestDispatcher("/views" + rolePath(user) + "/category-form.jsp").forward(req, resp);

	    } else if (uri.contains("category/edit")) {
	        int id = Integer.parseInt(req.getParameter("id"));
	        Category c = categoryService.findById(id);
	        req.setAttribute("category", c);
	        req.setAttribute("role", rolePath(user).replace("/", ""));
	        req.getRequestDispatcher("/views" + rolePath(user) + "/category-form.jsp").forward(req, resp);

	    } else if (uri.contains("category")) {
	        List<Category> list;
	        if (user.getRoleid() == 2) { // manager
	            list = categoryService.findByUserId(user.getId());
	        } else {
	            list = categoryService.findAll();
	        }
	        req.setAttribute("categories", list);
	        req.setAttribute("role", rolePath(user).replace("/", ""));
	        req.getRequestDispatcher("/views" + rolePath(user) + "/category-list.jsp").forward(req, resp);
	    }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Users user = (Users) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();

        if (uri.contains("category/add")) {
            String name = req.getParameter("name");
            Category c = new Category();
            c.setName(name);
            c.setUser(user);
            categoryService.insert(c);
        } else if (uri.contains("category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Category c = categoryService.findById(id);
            c.setName(name);
            categoryService.update(c);
        } else if (uri.contains("category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect(req.getContextPath() + rolePath(user) + "/category");
        }
        

        resp.sendRedirect(req.getContextPath() + rolePath(user) + "/category");
    }

    private String rolePath(Users user) {
        if (user.getRoleid() == 1) return "/user";
        if (user.getRoleid() == 2) return "/manager";
        return "/admin";
    }

}
