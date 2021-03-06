package servlet;

import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除选中用户处理
 */
@WebServlet("/deletecheck")
public class deletecheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] checks = request.getParameterValues("check");
        userDao userdao = new userDao();
        for (String check : checks) {
            userdao.delete(Integer.parseInt(check));
        }
        response.sendRedirect(request.getContextPath() + "/search");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
