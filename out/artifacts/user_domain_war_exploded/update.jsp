<%--
  Created by IntelliJ IDEA.
  User: ad725
  Date: 2019/3/17
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>update user info page</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<%
    String id = request.getParameter("id");
    System.out.println(id);
    session.setAttribute("id",id);
%>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">修改用户信息</h3>
    <form action="${pageContext.request.contextPath}/update" method="post">
        <div class="form-group">
            <label for="uname">姓名：</label>
            <input type="text" class="form-control" id="uname" name="uname" value="${user.uname}" />
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" value="男"/>男
            <input type="radio" name="sex" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}" />
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" id="address" class="form-control" >
                <option value="江西">江西</option>
                <option value="深圳" selected="selected">深圳</option>
                <option value="浙江">浙江</option>
            </select>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" value="${user.email}"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" id="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回"/>
        </div>
    </form>
</div>
<script>
    // 检测 , 用户名不能为空
    window.onload = function () {
        var uname = document.getElementById("uname");
        var submit = document.getElementById("submit");
        // 获取性别的radio数组
        var sex = document.getElementsByName("sex");
        // 获取所有的address的option数组
        var address = document.getElementById("address");
        submit.onclick = function () {
            if (uname.value == "" || uname.value == null) {
                alert("用户名不能为空");
                return;
            }
        }
        // 遍历数组对比值是否相同 , 相同则赋予checked属性
        for (var i = 0; i < sex.length; i += 1){
            if (sex[i].value == "${user.sex}"){
                sex[i].checked = true;
                break;
            }
        }
        // 遍历数组对比值是否相同 , 相同赋予selected属性
        for (var i = 0; i < address.length; i += 1){
            console.log(address[i].value + ":::${user.address}")
            if (address[i].value == "${user.address}"){
                address[i].selected = true;
                break;
            }
        }
    }
</script>
</body>
</html>
