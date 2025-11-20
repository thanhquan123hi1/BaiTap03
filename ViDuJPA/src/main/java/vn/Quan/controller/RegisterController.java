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

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()
                || fullname.isEmpty() || phone.isEmpty()) {
            req.setAttribute("alert", "Vui lòng nhập đầy đủ thông tin!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Kiểm tra trùng (dùng JPA)
        if (service.checkExistUsername(username)) {
            req.setAttribute("alert", "Tài khoản đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistEmail(email)) {
            req.setAttribute("alert", "Email đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }
        if (service.checkExistPhone(phone)) {
            req.setAttribute("alert", "Số điện thoại đã tồn tại!");
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Tạo user mới
        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setFullname(fullname);
        newUser.setPhone(phone);
        newUser.setImages("default.png");
        newUser.setRoleid(1); // mặc định user

        try {
            service.create(newUser);
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (Exception e) {
            req.setAttribute("alert", "Lỗi hệ thống: " + e.getMessage());
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
