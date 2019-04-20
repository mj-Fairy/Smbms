<%--
  Created by IntelliJ IDEA.
  User: MuQiLong-PC
  Date: 2019/3/7
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<fm:form method="post" modelAttribute="user">
    <fm:errors path="userCode"/>
    用户编码:<fm:input path="userCode"/></br>
    <fm:errors path="userName"/>
    用户名称:<fm:input path="userName"/></br>
    <fm:errors path="userPassword"/>
    用户密码:<fm:password path="userPassword"/></br>
    <fm:errors path="birthday"/>
    用户生日:<fm:input path="birthday" Class="Wdate" readonly="readonly" onclick="WdatePicker();"/></br>
    用户地址:<fm:input path="address"/></br>
    联系电话:<fm:input path="phone"/></br>
    用户角色:
    <fm:radiobutton path="userRole" value="1"/>系统管理员
    <fm:radiobutton path="userRole" value="2"/>经理
    <fm:radiobutton path="userRole" value="3" checked="checked"/>普通用户
    </br>
    <input type="submit" value="保存"/>
</fm:form>
</body>
</html>
