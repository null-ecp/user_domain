package servlet;

import bean.spilpage;
import dao.userDao;

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
        // 设置编码
        request.setCharacterEncoding("utf-8");
        // 获取条件参数集合
        Map<String, String[]> condition = request.getParameterMap();
        String pageindex = request.getParameter("pageindex");
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
        // 获取user集合
        spilpage.setList(userDao.findbycond(condition, spilpage));
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
