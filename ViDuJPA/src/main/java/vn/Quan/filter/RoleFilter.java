package vn.Quan.filter;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import vn.Quan.entity.UserEntity;
import vn.Quan.utils.Constant;

@WebFilter(urlPatterns = "/*")
public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        String ctx = req.getContextPath();

        HttpSession session = req.getSession(false);
        UserEntity user = null;

        if (session != null) {
            user = (UserEntity) session.getAttribute(Constant.SESSION_LOGIN);
        }

        if (user != null) {
            int role = user.getRoleid();

            if (path.startsWith(ctx + "/admin")) {
                if (role != 3) {
                    resp.sendRedirect(ctx + "/waiting");
                    return;
                }
            }

            if (path.startsWith(ctx + "/manager")) {
                if (role != 2) {
                    resp.sendRedirect(ctx + "/waiting");
                    return;
                }
            }

            if (path.startsWith(ctx + "/user")) {
                if (role != 1) {
                    resp.sendRedirect(ctx + "/waiting");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }
}
