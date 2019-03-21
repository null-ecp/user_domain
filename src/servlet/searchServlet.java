package servlet;

import Util.JDBCUtil;
import bean.spilpage;
import bean.user;
import dao.userDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 获取查询的用户列表
 */
@WebServlet("/search")
public class searchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("login_msg");
        // 获取页面参数
        String pageindex = request.getParameter("pageindex");
        Map<String, String[]> condition = request.getParameterMap();
        // 创建事件处理类
        Service service = new Service();
        spilpage spilpage = service.inituserlist(pageindex, condition);
        // 设置域属性
        request.setAttribute("page",spilpage);
        // 跳转页面
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
