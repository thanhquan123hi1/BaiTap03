package vn.Quan.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // XÓA SESSION
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.removeAttribute(Constant.SESSION_LOGIN);
            session.invalidate();
        }

        // XÓA COOKIE rememberMe
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        resp.addCookie(cookie);

        // Quay về login
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
