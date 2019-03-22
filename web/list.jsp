<%--
  Created by IntelliJ IDEA.
  User: ad725
  Date: 2019/3/17
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>user info list</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">

        <form class="form-inline" action="${pageContext.request.contextPath}/searchbycond" method="post" >
            <div class="form-group">
                <label for="uname">姓名</label>
                <input type="text" class="form-control" id="uname" name="uname">
            </div>
            <div class="form-group">
                <label for="address">籍贯</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>

            <div class="form-group">
                <label for="email">邮箱</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" onclick="del_submit()">删除选中</a>

    </div>

    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" name="checkall" id="checkall"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <form id="del_check" action="${pageContext.request.contextPath}/deletecheck" method="post">
            <c:if test="${not empty page.list}">
                <c:forEach items="${page.list}" var="user">
                    <tr>
                        <td><input type="checkbox" name="check" id="check" value="${user.id}"></td>
                        <td>${user.id}</td>
                        <td>${user.uname}</td>
                        <td>${user.sex}</td>
                        <td>${user.age}</td>
                        <td>${user.address}</td>
                        <td>${user.email}</td>
                        <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/checkuser?id=${user.id}">修改</a>&nbsp;
                            <a class="btn btn-default btn-sm" href="javascript:deletecheck(${user.id})">删除</a></td>
                    </tr>
                </c:forEach>
            </c:if>
        </form>
    </table>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <li>
                    <a href="javascript:checkprev(${page.pageindex})" id="prev" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <%-- 设置页码显示数 --%>
                <%-- 总页码小于6则正常显示 --%>
                <c:if test="${page.pagecount < 6}">
                    <c:set var="start" value="1" />
                    <c:set var="over" value="${page.pagecount}" />
                </c:if>
                <%-- 大于5 则显示当前页面前后两条 --%>
                <c:if test="${page.pagecount > 5}">
                    <c:set var="start" value="${page.pageindex - 2}" />
                    <c:set var="over" value="${page.pageindex + 2}" />

                    <%-- 当页码开始动态显示 第一条页码小于1则恢复默认显示1-5 --%>
                    <c:if test="${start < 1}">
                        <c:set var="start" value="1" />
                        <c:set var="over" value="5" />
                    </c:if>

                    <%-- 当页码走到最后则显示最后5页 --%>
                    <c:if test="${page.pagecount < over}">
                        <c:set var="start" value="${page.pagecount - 4}" />
                        <c:set var="over" value="${page.pagecount}" />
                    </c:if>
                </c:if>

                <%--<c:if test="${over > page.pagecount}">--%>
                    <%--<c:set var="start" value="${page.pagecount -5}" />--%>
                    <%--<c:set var="over" value="${page.pagecount}" />--%>
                <%--</c:if>--%>

                <c:forEach begin="${start}" end="${over}" var="index">

                    <c:if test="${index == page.pageindex}">
                        <li  class="active"><a href="${pageContext.request.contextPath}/searchbycond?pageindex=${index}&uname=${condition.uname[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${index}</a></li>
                    </c:if>

                    <c:if test="${index != page.pageindex}">
                        <li><a href="${pageContext.request.contextPath}/searchbycond?pageindex=${index}&uname=${condition.uname[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${index}</a></li>
                    </c:if>
                </c:forEach>
                <li>
                    <a href="javascript:checknext(${page.pageindex})" aria-label="Next" id="next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px;">
                    共${page.total}条记录，共${page.pagecount}页
                </span>

            </ul>
        </nav>

    </div>
</div>/
<script>
    window.onload = function () {
        var checkall = document.getElementById("checkall")
        var checks = document.getElementsByName("check")
        checkall.onclick = function () {
            for (var i = 0; i< checks.length; i += 1){
                checks[i].checked = checkall.checked
            }
        }
    }
    function deletecheck(id) {
        if (confirm("确定删除该用户么?")){
            <%--location是js内置对象 , 在js中 , 无法获取到${user}属性 , 所以 需要为方法设置参数--%>
            location.href = "${pageContext.request.contextPath}/delete?id="+id
        }
    }
    function del_submit() {
        var del_from = document.getElementById("del_check")
        var users = document.getElementsByName("check")

        if (confirm("确定删除选中用户?")) {
            var len = 0;
            for (var i = 0; i < users.length; i += 1){
                if (users[i].checked == true){
                    len += 1
                    break;
                }
            }
            if (len > 0){
                del_from.submit();
            } else {
                alert("请选中用户后再删除")
            }
        }
    }
    function checknext(index) {
        var next = document.getElementById("next")
        if (Number(index) < Number("${page.pagecount}")){
            location.href = "${pageContext.request.contextPath}/searchbycond?pageindex=" + Number(index + 1) +
                    "&uname=${condition.uname[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
        } else {
            location.href = "#"
            next.enabled = false;
        }
    }
    function checkprev(index) {
        var next = document.getElementById("prev")
        if (Number(index) > 1){
            location.href = "${pageContext.request.contextPath}/searchbycond?pageindex=" + Number(index - 1) +
                "&uname=${condition.uname[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
        } else {
            location.href = "#"
            next.enabled = false;
        }
    }
</script>
</body>
</html>
