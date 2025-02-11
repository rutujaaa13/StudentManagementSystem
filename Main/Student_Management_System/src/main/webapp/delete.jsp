<%@page import="com.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file='base.html' %>
</head>
<body>

<%
Student s =(Student)request.getAttribute("obj");

%>
<div class="container">
<%@ include file='navbar.html'%>
</div>
<div class="container">
<h3 class="p-3 mb-2 bg-info text-white">Are You Sure That You Want To Delete: "<%=s.getName() %>"</h3>
<div class="text-center">
<form action="delete?id=<%=s.getId() %>" method="POST">
<input type="submit" class="  btn btn-danger btn-sm" value="Yes">
</form>
<br>
<a href="list" class="btn btn-success btn-sm">NO</a>
</div>



</div>

</body>
</html>