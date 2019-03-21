package servlet;

import Util.JDBCUtil;
import bean.spilpage;
import bean.user;
import dao.userDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        int index = 1;
        // 判断是否是下一页 或者 上一页
        if (pageindex == null || pageindex.equals("")){
            index = 1;
        } else {
            index = Integer.parseInt(pageindex);
        }
        // 创建
        userDao userDao = new userDao();
        spilpage spilpage = new spilpage();
        // 设置页面对象
        spilpage.setTotal(userDao.getCount(condition));
        spilpage.setPageindex(index);
        spilpage.setPagecount();
        // 查询数据
        List<user> list = userDao.findbypage(spilpage);
        spilpage.setList(list);
        // 设置域属性
        request.setAttribute("page",spilpage);
        // 跳转页面
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
