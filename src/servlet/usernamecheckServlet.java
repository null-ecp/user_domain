package servlet;

import bean.user;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkusername")
public class usernamecheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置json对应的响应头
        response.setContentType("application/json;charset=utf-8");
        String uname = request.getParameter("uname");
        // 创造一个map来存储
        Map<String, Object> map = new HashMap<String, Object>();
        // 创建json处理对象
        ObjectMapper objectMapper = new ObjectMapper();
        // 创建dao处理
        userDao userDao = new userDao();
        user user = userDao.findbyuname(uname);
        if (user != null){
            map.put("hasuname",false);
            map.put("msg","用户名 "+uname+" 已存在");
            objectMapper.writeValue(response.getWriter(), map);
        } else {
            map.put("hasuname",true);
            map.put("msg","用户名 "+uname+" 可用");
            objectMapper.writeValue(response.getWriter(), map);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
