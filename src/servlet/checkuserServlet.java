package servlet;

import bean.user;
import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 更新用户信息界面的信息回显处理
 */
@WebServlet("/checkuser")
public class checkuserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取传递的id
        int id = Integer.parseInt(request.getParameter("id"));
        // 创建dao对象
        userDao userdao = new userDao();
        user user = userdao.findbyid(id);
        // 设置请求域对象 , 将请求转发
        request.setAttribute("user",user);
        request.getSession().setAttribute("id", id);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
