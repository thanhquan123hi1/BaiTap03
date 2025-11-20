package vn.Quan.controller.manager;

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

@WebServlet(urlPatterns = "/manager/categories/edit")
@MultipartConfig
public class CategoryEditController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ICategoryService cateService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        CategoryEntity cate = cateService.findById(id);

        if (cate == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
            return;
        }

        req.setAttribute("cate", cate);
        req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession(false);
        UserEntity user = (UserEntity) session.getAttribute(Constant.SESSION_LOGIN);

        int id = Integer.parseInt(req.getParameter("cate_id"));
        String name = req.getParameter("cate_name");

        CategoryEntity cate = cateService.findById(id);

        if (cate == null) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
            return;
        }

        // KIỂM TRA QUYỀN
        if (cate.getUser().getId() != user.getId()) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
            return;
        }

        Part file = req.getPart("iconFile");
        String fileName = file.getSubmittedFileName();

        String uploadPath = req.getServletContext().getRealPath("/uploads/category/");
        String finalFile = cate.getIcons();

        if (fileName != null && !fileName.isEmpty()) {

            File oldFile = new File(uploadPath + cate.getIcons());
            if (oldFile.exists()) oldFile.delete();

            finalFile = System.currentTimeMillis() + "_" + fileName;
            file.write(uploadPath + finalFile);
        }

        cate.setCateName(name);
        cate.setIcons(finalFile);

        try {
            cateService.update(cate);
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } catch (Exception e) {
            req.setAttribute("alert", e.getMessage());
            req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);
        }
    }
}
