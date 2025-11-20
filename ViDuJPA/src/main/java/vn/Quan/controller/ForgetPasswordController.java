package vn.Quan.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.Quan.entity.UserEntity;
import vn.Quan.service.IUserService;
import vn.Quan.service.impl.UserService;

@WebServlet(urlPatterns = {"/forget"})
public class ForgetPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/forget.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        if (email == null || email.isEmpty()) {
            req.setAttribute("alert", "Email không được rỗng!");
            req.getRequestDispatcher("/views/forget.jsp").forward(req, resp);
            return;
        }

        // Dùng JPA để tìm user
        UserEntity user = service.findByEmail(email);

        if (user == null) {
            req.setAttribute("alert", "Email không tồn tại!");
            req.getRequestDispatcher("/views/forget.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("alert", "Mật khẩu của bạn là: " + user.getPassword());
        req.getRequestDispatcher("/views/forget.jsp").forward(req, resp);
    }
}
