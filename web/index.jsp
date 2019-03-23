<%--
  Created by IntelliJ IDEA.
  User: ad725
  Date: 2019/3/17
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>index page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
  </head>
  <body>
  <div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/login" method="post">
      <div class="form-group">
        <label for="id">用户名：</label>
        <input type="text" name="id" class="form-control" id="id" placeholder="请输入用户名"/>
      </div>

      <div class="form-group">
        <label for="id">密码：</label>
        <input type="password" name="pd" class="form-control" id="pd" placeholder="请输入密码"/>
      </div>

      <div class="form-inline">
        <label for="incode">验证码：</label>
        <input type="text" name="checkcode" class="form-control" id="incode" placeholder="请输入验证码" style="width: 120px;"/>
          <img src="${pageContext.request.contextPath}/getcode" title="看不清点击刷新" id="checkcode"/>
      </div>
      <hr/>
      <div class="form-group" style="text-align: center;">
        <input class="btn btn btn-primary" type="submit" value="登录">
      </div>
    </form>
    <c:if test="${not empty login_msg}">
      <!-- 出错显示的信息框 -->
      <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
          <span>&times;</span>
        </button>
        <strong>登录失败!</strong><br>
        <span>${login_msg}</span>
      </div>
    </c:if>
  </div>
  <script>
    window.onload = function () {
      var checkcode = document.getElementById("checkcode")

      checkcode.onclick = function () {
        checkcode.src = "${pageContext.request.contextPath}/getcode?time=" + new Date().getTime()
      }
    }
  </script>
  </body>
</html>
