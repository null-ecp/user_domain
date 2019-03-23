package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


import Util.checkcode;

/**
 * 获取验证码
 */
@WebServlet("/getcode")
public class getcheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedImage bimg = new BufferedImage(80, 40, BufferedImage.TYPE_INT_RGB);
        checkcode.getcheckcode(bimg);
        ImageIO.write(bimg, "jpg", response.getOutputStream());
        // 上传验证码到session
        System.out.println(checkcode.getCode());
        request.getSession().setAttribute("code",checkcode.getCode());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
