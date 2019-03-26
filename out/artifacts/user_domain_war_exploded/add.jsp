<%--
  Created by IntelliJ IDEA.
  User: ad725
  Date: 2019/3/17
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->

    <title>add user page</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">添加联系人页面</h3>
    <form action="${pageContext.request.contextPath}/adduser" method="post">
        <div class="form-group">
            <label for="uname">姓名：</label>
            <input type="text" class="form-control" id="uname" name="uname" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" value="男" checked="checked"/>男
            <input type="radio" name="sex" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="address">
                <option value="深圳">深圳</option>
                <option value="浙江">浙江</option>
                <option value="江西">江西</option>
            </select>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" />
        </div>
    </form>
    <div id="tips_box" class="alert alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <span id="tips_msg">用户名已存在 , 请更换</span>
    </div>
</div>
<script>
    $(function (){
        $("#tips_box").hide()
        $("#uname").blur(function () {
            recv = $.post("${pageContext.request.contextPath}/checkusername",
                {"uname":$("#uname").val()},function (data,status) {
                    // 判断名字是否重复
                    if (data.hasuname) {
                        $("#tips_box").hide()
                        $("#tips_msg").html(data.msg)
                        $("#tips_box").removeClass("alert-danger").addClass("alert-success").slideDown(1000)
                    } else {
                        $("#tips_box").hide()
                        $("#tips_msg").html(data.msg)
                        $("#tips_box").removeClass("alert-success").addClass("alert-danger").slideDown(1000)
                    }
                }, "json");

        });
    });
</script>
</body>
</html>
