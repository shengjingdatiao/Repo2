<%--
  Created by IntelliJ IDEA.
  User: liuhu
  Date: 2019/10/20
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function del(id, currentPage, pageSize) {
            if (confirm("你他娘的确定要删除?")) {
                location.href = "${pageContext.request.contextPath}/user/delete?id=" + id +"&currentPage="+currentPage+"&pageSize="+pageSize;
            }
        }
        function delSelected() {
            if (confirm("你确定要删除?")){
                if( $("input[name='uid']:checked").length>0 ){
                    $("#form").submit()
                }else {
                    alert("您没有选中可删除的条目!")
                }
            }

        }
        /*window.onload = function () {
            document.getElementById("delSelect").onclick = function () {
                if (confirm("你他娘的确定要删除?")) {
                    //判断是否有按钮被选中
                    var flag = false;
                    //遍历所有按钮
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        if (cbs[i].checked) {
                            // 有一个被选中了
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //删除
                        document.getElementById("form").submit();
                    }
                }
            };
            //全选按钮
            document.getElementById("firstCb").onclick = function () {
                if (confirm("你他娘的确定要删除?")) {

                    //遍历所有按钮
                    var cbs = document.getElementsByName("uid");
                    for (var i = 0; i < cbs.length; i++) {
                        cbs[i].checked = this.checked;
                    }
                }
            }

        }*/
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/user/findAll" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" name="name" value="${param.name}" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" name="adress" value="" id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" class="form-control" name="email"  value="${param.email}"  id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查找</button>
        </form>
    </div>
    <div style="float: right;margin:6px ">
        <a class="btn btn-primary" href="/user/addUI">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void (0);" onclick="delSelected();">删除选中</a>
    </div>
    <form id="form" action="${pageContext.request.contextPath}/user/deleteSelect" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count+(pageBean.currentPage-1)*pageBean.pageSize}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.province.name}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/user/findById?id=${user.id}">修改</a>&nbsp
                        <a class="btn btn-default btn-sm" href="javascript:del(${user.id},${pageBean.currentPage},${pageBean.pageSize})">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pageBean.currentPage==1}">
                <li class="disabled">
                    <a href="javascript:void (0)" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                </c:if>
                <c:if test="${pageBean.currentPage!=1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pageBean.currentPage-1}&pageSize=${pageBean.pageSize}&name=${param.name}&email=${param.email}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                    <c:if test="${pageBean.currentPage==i}">
                <li class="active">

                    <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${i}&pageSize=${pageBean.pageSize}&name=${param.name}&email=${param.email}">${i}</a>
                </li></c:if>
                    <c:if test="${pageBean.currentPage!=i}">
                        <li >

                            <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${i}&pageSize=${pageBean.pageSize}&name=${param.name}&email=${param.email}">${i}</a>
                        </li></c:if>

                </c:forEach>
                <c:if test="${pageBean.currentPage==pageBean.totalPage}">
                <li class="disabled">
                    <a href="javascript:void (0)" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                </c:if>
                <c:if test="${pageBean.currentPage < pageBean.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/user/findAll?currentPage=${pageBean.currentPage+1}&pageSize=${pageBean.pageSize}&name=${param.name}&email=${param.email}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 25px;margin-left: 10px">共${pageBean.totalCount}条记录,共${pageBean.totalPage}页</span>
            </ul>
        </nav>

    </div>
</div>
</body>
</html>