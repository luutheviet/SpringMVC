<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 10/01/2022
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Welcome Viet Nam</title>
</head>
<body>
    <h2>Please Input Student Infomation</h2>
    <form:form method="post" action="save">
<%--        <form:hidden path="get" />--%>
<%--        <form:hidden path="id" />--%>
        <table>
            <tr>
                <td>Name :</td>
                <td>
                    <form:input type="text" path="name" />
                </td>
            </tr>
            <tr>
                <td>Age :</td>
                <td>
                    <form:input type="number" path="age" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="submit">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>