package filter;

import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证用户是否登录
 */
@WebFilter(value = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class logincheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 强转类型 方便处理请求
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        // 获取请求uri
        String uri = request.getRequestURI();
        // 资源文件过滤
        if (uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/")){
            chain.doFilter(req, resp);
        }
        // 登录页面过滤
        if (uri.contains("getcode") || uri.contains("index.jsp") || uri.equals("/user/")){
//            request.getSession().removeAttribute("login_msg");
            chain.doFilter(req, resp);
        } else {
            // 非登录界面则判断是否登录
            Boolean loginflag = (Boolean)request.getSession().getAttribute("loginflag");
            if (loginflag != null){
                chain.doFilter(req, resp);
            } else {
                request.getSession().setAttribute("login_msg", "未登录 , 请登录后访问");
                response.sendRedirect("index.jsp");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
