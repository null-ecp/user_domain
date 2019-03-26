package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 敏感言论过滤
 */
// 敏感言论都是直接提交的 所以这里不用配置转发
@WebFilter("/*")
public class fuckFilter implements Filter {
    // 创建列表来存储敏感词汇
    private List<String> fucklist = new ArrayList<String>();
    private String path = "";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // 使用代理对象增强功能
        ServletRequest pro_req = (ServletRequest) Proxy.newProxyInstance(
                req.getClass().getClassLoader(),                    // 被代理对象的类字节码
                req.getClass().getInterfaces(),                     // 被代理对象的实现接口
                new InvocationHandler() {                           // 方法增强区
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 判断执行的方法是否为需要被增强的方法
                        if (method.getName().equals("getParameter")) {
                            // 执行方法 , 获取返回值 -- String类型 , 如果返回值有敏感词则过滤
                            String value = (String) method.invoke(req, args);
                            if (value != null){
                                for (String s : fucklist) {
                                    if (value.contains(s)){
                                        value = value.replace(s, "***");
                                        break;
                                    }
                                }
                            }
                            return value;
                        } else {
                            // 没有敏感词汇则正常执行
                            Object o = method.invoke(req, args);
                            return o;
                        }
                    }
                });
        // 使用代理对象来完成操作
        chain.doFilter(pro_req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        // 动态加载敏感词汇配置
        ServletContext servletContext = config.getServletContext();
        // 获取路径
        path = servletContext.getRealPath("/WEB-INF/classes/fuckfilter.txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            // 读取和做判断
            String line;
            // 循环读取数据
            while ((line = br.readLine()) != null){
                fucklist.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
