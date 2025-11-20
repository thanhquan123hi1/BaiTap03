package vn.Quan.controller.admin;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.service.ICategoryService;
import vn.Quan.service.impl.CategoryService;

@WebServlet(urlPatterns = "/admin/categories/delete")
public class CategoryDeleteController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        CategoryEntity cate = cateService.findById(id);

        String uploadPath = req.getServletContext().getRealPath("/uploads/category/");

        if (cate != null && cate.getIcons() != null) {
            File oldFile = new File(uploadPath + cate.getIcons());
            if (oldFile.exists()) oldFile.delete();
        }

        try {
            cateService.delete(id, cate.getUser().getId());
        } catch (Exception e) {
            req.setAttribute("alert", e.getMessage());
        }

        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }
}
