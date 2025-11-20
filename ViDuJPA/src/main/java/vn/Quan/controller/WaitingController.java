package vn.Quan.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.Quan.entity.UserEntity;
import vn.Quan.utils.Constant;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute(Constant.SESSION_LOGIN) == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserEntity user = (UserEntity) session.getAttribute(Constant.SESSION_LOGIN);
        req.setAttribute("username", user.getUsername());

        int role = user.getRoleid();

        if (role == 3) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else if (role == 2) {
            resp.sendRedirect(req.getContextPath() + "/manager/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
