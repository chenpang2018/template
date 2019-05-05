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

    <form action="/user/saveUser.action" id="Form" name="Form">
        <table border="1">
            <tr>
                <td>用户号</td>
                <td><input name="userNo" id="userNo" type="text" value="${userNo}" title="用户号"/></td>
            </tr>
            <tr>
                <td>用户名</td>
                <td><input name="userName" id="userName" type="text" value="${userName}" title="用户名"/></td>
            </tr>
            <tr>
                <td>
                    <a href="#" onclick="save()">保存</a>
                    <a href="#" onclick="save()">取消</a>
                </td>
            </tr>
        </table>
    </form>

<script type="text/javascript">
    function save() {
        if(!confirm("确认保存？")){
            return;
        }
        $("#Form").submit();
    }
</script>
<script type="text/javascript" src="/js/jquery-1.11.3.js"></script>

</body>
</html>
