package servlet;

import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户添加界面
 */
@WebServlet("/adduser")
public class adduserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 创建map集合用于存值
        Map<String, String> values = new HashMap<String, String>();
        values.put("uname",request.getParameter("uname"));
        // 如果age未输入则默认为0
        values.put("age",request.getParameter("age") == "" ? "0":request.getParameter("age"));
        values.put("sex",request.getParameter("sex"));
        values.put("address",request.getParameter("address"));
        values.put("email",request.getParameter("email"));

        System.out.println(values);

        // 添加用户
        userDao userDao = new userDao();
        userDao.adduser(values);

        // 添加完设置提示信息 并跳转回用户列表界面
        request.getSession().setAttribute("tip_msg","用户信息添加成功");
        response.sendRedirect(request.getContextPath()+"/search");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
