<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>

<body>
    <form action="/user/getUserList.action" id="Form" name="Form">
        <input type="hidden" id="userNo" name="userNo"/>
        <table>
            <tr>
                <a href="#" onclick="addUser()">新增用户</a>
            </tr>
        </table>
        <table id="myTable" border="1">
            <thead>
                <tr>
                    <th>
                        <label><input type="checkbox" id="zcheckbox"/><span></span></label>
                    </th>
                    <th>序号</th>
                    <th>创建时间</th>
                    <th>用户号</th>
                    <th>用户名</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty list}">
                        <c:forEach items="${list}" var="var" varStatus="vs">
                            <tr>
                                <td style="width: 30px;">
                                    <label><input type='checkbox' name='ids' value="${var.id}"/><span></span></label>
                                </td>
                                <td>${vs.index + 1}</td>
                                <td>${var.createTime}</td>
                                <td>${var.userNo}</td>
                                <td>${var.userName}</td>
                                <td><a href="#" onclick="userDetail(${var.userNo})">查看详情</a> </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </tbody>
        </table>
    </form>


<script type="text/javascript">
    function addUser() {
        var url = "<%=basePath%>/user/toAddUser.action"
        var x = document.getElementById("Form");
        x.action = "/user/toAddUser.action";
        x.submit();
    }

    function userDetail(userNo) {
        var x = document.getElementById("Form");
        x.action = "/user/getUserByUserNo.action";
        document.getElementById("userNo").value = userNo;
//        x.setAttribute("userNo", userNo);
        x.submit();
    }
</script>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>
</body>
</html>
