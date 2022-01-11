<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11/01/2022
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<tiles:insertDefinition name="studentTemplate">
    <tiles:putAttribute name="body"/>
</tiles:insertDefinition>
<html>
<head>
    <title>List Studen</title>
</head>
<body>
    <h2>List of students</h2>
    <table border="1">
        <tr>
            <td>ID</td>
            <td>Name</td>
            <td>Age</td>
        </tr>
        <c:forEach items="${students}" var="student">
            <tr>
                <td>${student.getId()}</td>
                <td>${student.getName()}</td>
                <td>${student.getAge()}</td>
                <td><a href="delete/${student.getId()}">delete</a></td>
                <td><a  href="get/${student.getId()}">edit</a></td>
                <td><a href="javascript:view(${student.getId()})">${student.getName()}</a></td>
            </tr>
        </c:forEach>
    </table>

    <dialog id="viewStudent" style="width: 50%; border: 1px solid black;">
        <div id="content"></div>
        <button id="hide" >Close</button>
    </dialog>

    <script>
        function view(id){
            let xmlHttp = new XMLHttpRequest();
            xmlHttp.open("GET", "json/" + id, true);
            xmlHttp.onload = function (){
                if(this.status != 200) return;
                let student = JSON.parse(this.responseText);
                document.getElementById("content").innerHTML = "Name " + student.name;
                let dialog = document.getElementById("viewStudent").show();
                console.log(this.responseText);
            }
            xmlHttp.send();
        }

        (function (){
            let dialog = document.getElementById("viewStudent");
            document.getElementById("hide").onclick = ()=>{
                dialog.close();
            }
        })();

    </script>
</body>
</html>
