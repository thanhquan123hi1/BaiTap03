package vn.Quan.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.Quan.entity.UserEntity;
import vn.Quan.service.IUserService;
import vn.Quan.service.impl.UserService;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    IUserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute(Constant.SESSION_LOGIN) != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(Constant.COOKIE_REMEMBER)) {
                    req.setAttribute("rememberedUser", c.getValue());
                }
            }
        }

        req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("uname");
        String password = req.getParameter("psw");
        String remember = req.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);

        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Đăng nhập bằng JPA
        UserEntity user = service.login(username, password);

        if (user == null) {
            req.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
            return;
        }

        // Tạo session
        HttpSession session = req.getSession(true);
        session.setAttribute(Constant.SESSION_LOGIN, user);

        // Cookie remember me
        if (isRememberMe) {
            saveRememberMe(resp, username);
        } else {
            clearRememberMe(resp);
        }

        // Chuyển hướng theo ROLE
        int role = user.getRoleid();

        if (role == 3) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (role == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/user/home");
        }
    }

    private void saveRememberMe(HttpServletResponse resp, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(60 * 60 * 24 * 7); // 7 ngày
        cookie.setPath("/");
        resp.addCookie(cookie);
    }

    private void clearRememberMe(HttpServletResponse resp) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);
    }
}
