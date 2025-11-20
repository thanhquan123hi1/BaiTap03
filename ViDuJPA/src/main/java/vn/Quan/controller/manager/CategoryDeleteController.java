package vn.Quan.controller.manager;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.entity.UserEntity;
import vn.Quan.service.ICategoryService;
import vn.Quan.service.impl.CategoryService;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = "/manager/categories/delete")
public class CategoryDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute(Constant.SESSION_LOGIN) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserEntity user = (UserEntity) session.getAttribute(Constant.SESSION_LOGIN);

        int id = Integer.parseInt(req.getParameter("id"));
        CategoryEntity cate = cateService.findById(id);

        if (cate == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
            return;
        }

        if (user.getRoleid() != 2 && cate.getUser().getId() != user.getId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
            return;
        }

        String uploadPath = req.getServletContext().getRealPath("/uploads/category/");
        if (cate.getIcons() != null) {
            File f = new File(uploadPath + cate.getIcons());
            if (f.exists()) f.delete();
        }

        try {
            cateService.delete(id, cate.getUser().getId());
        } catch (Exception e) {
            req.setAttribute("alert", e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/manager/home");
    }
}
