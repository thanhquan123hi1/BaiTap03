package vn.Quan.controller.admin;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.Quan.entity.CategoryEntity;
import vn.Quan.entity.UserEntity;
import vn.Quan.service.ICategoryService;
import vn.Quan.service.impl.CategoryService;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = "/admin/categories/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("cate_name");

        Part filePart = req.getPart("iconFile");
        String fileName = filePart.getSubmittedFileName();

        String uploadPath = req.getServletContext().getRealPath("/uploads/category/");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String savedFile = null;

        if (fileName != null && !fileName.isEmpty()) {
            savedFile = System.currentTimeMillis() + "_" + fileName;
            filePart.write(uploadPath + savedFile);
        }

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute(Constant.SESSION_LOGIN);

        CategoryEntity cate = new CategoryEntity();
        cate.setCateName(name);
        cate.setIcons(savedFile);
        cate.setUser(user);

        try {
            cateService.create(cate);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } catch (Exception e) {
            req.setAttribute("alert", e.getMessage());
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
    }
}
