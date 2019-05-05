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

    <form action="/user/getUserByUserNo.action" id="Form" name="Form">
        <table border="1">
            <tr>
                <td>用户号</td>
                <td>${user.userNo}</td>
            </tr>
            <tr>
                <td>用户名</td>
                <td>${user.userName}</td>
            </tr>
        </table>
    </form>

<script type="text/javascript">
</script>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>

</body>
</html>
