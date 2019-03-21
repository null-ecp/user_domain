package servlet;

import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除用户信息
 */
@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建dao实例
        userDao userdao = new userDao();
        // 获取请求的参数
        int id =Integer.parseInt(request.getParameter("id"));
        // 删除用户
        userdao.delete(id);
        // 重新跳转到查询界面
        request.getSession().setAttribute("tip_msg","用户记录删除完成");
        response.sendRedirect(request.getContextPath() + "/search");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
