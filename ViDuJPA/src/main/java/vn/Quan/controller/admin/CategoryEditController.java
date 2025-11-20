package vn.Quan.controller.admin;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.service.ICategoryService;
import vn.Quan.service.impl.CategoryService;

@WebServlet(urlPatterns = "/admin/categories/edit")
@MultipartConfig
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        CategoryEntity cate = cateService.findById(id);

        req.setAttribute("cate", cate);
        req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("cate_id"));
        String name = req.getParameter("cate_name");

        CategoryEntity cate = cateService.findById(id);
        if (cate == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
            return;
        }

        Part filePart = req.getPart("iconFile");
        String newFileName = filePart.getSubmittedFileName();

        String uploadPath = req.getServletContext().getRealPath("/uploads/category/");
        String finalFile = cate.getIcons();

        if (newFileName != null && !newFileName.isEmpty()) {

            File oldFile = new File(uploadPath + cate.getIcons());
            if (oldFile.exists()) oldFile.delete();

            finalFile = System.currentTimeMillis() + "_" + newFileName;
            filePart.write(uploadPath + finalFile);
        }

        cate.setCateName(name);
        cate.setIcons(finalFile);

        try {
            cateService.update(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } catch (Exception e) {
            req.setAttribute("alert", e.getMessage());
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        }
    }
}
