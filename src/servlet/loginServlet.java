package servlet;

import Util.checkcode;
import dao.adminimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录处理
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入
        String id = request.getParameter("id");
        String pd = request.getParameter("pd");
        String incode = request.getParameter("checkcode");
        // 获取验证码
        String code = (String) request.getSession().getAttribute("code");
        request.getSession().removeAttribute("code");
        // 判断验证码输入
        if (incode != null && checkcode.checkinput(incode, code)){
            adminimpl adm = new adminimpl();
            if (adm.login(id,pd)) {
                request.getSession().setAttribute("loginflag", true);
                response.sendRedirect(request.getContextPath() + "/search");
                return;
            } else {
                request.getSession().setAttribute("login_msg","用户名或密码输入错误");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        } else {
            request.getSession().setAttribute("login_msg","验证码错误");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
