package servlet;

import bean.spilpage;
import dao.userDao;
import service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 条件查询处理页面
 */
@WebServlet("/searchbycond")
public class searchbycondServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取条件参数集合
        Map<String, String[]> condition = request.getParameterMap();
        String pageindex = request.getParameter("pageindex");
        // 创建事件处理类
        Service service = new Service();
        spilpage spilpage = service.inituserlist(pageindex, condition);
        // 设置域属性
        request.setAttribute("page", spilpage);
        request.setAttribute("condition", condition);
        // 跳转页面
        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
