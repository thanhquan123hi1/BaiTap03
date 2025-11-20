package vn.Quan.controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.entity.UserEntity;
import vn.Quan.service.ICategoryService;
import vn.Quan.service.impl.CategoryService;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = "/admin/home")
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession ses = req.getSession(false);
        if (ses == null || ses.getAttribute(Constant.SESSION_LOGIN) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserEntity user = (UserEntity) ses.getAttribute(Constant.SESSION_LOGIN);

        if (user.getRoleid() != 3) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        List<CategoryEntity> list = cateService.findAll();

        req.setAttribute("listcate", list);
        req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);
    }
}
