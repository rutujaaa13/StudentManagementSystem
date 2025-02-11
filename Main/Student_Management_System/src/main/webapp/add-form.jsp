<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file='base.html' %>
</head>
<body>
<div class="container">
<%@ include file='navbar.html'%>
</div>
<div class="container">
<h2 class="text-center">Add Students Details Here!!!!</h2>
<hr>


<form action="add" method="post">
  <div class="form-group">
    <label for="inputName">Student Name</label>
    <input type="text" name="sname" class="form-control" id="inputName" aria-describedby="emailHelp" placeholder="Enter Student Name">
   
    </div>
      <div class="form-group">
     <label for="inputMarks">Student Marks</label>
    <input type="text" name="smarks" class="form-control" id="inputMarks" aria-describedby="emailHelp" placeholder="Enter Student Marks">
      </div>
       <div class="form-group">
        <label for="inputRollNum">Student Roll Number</label>
    <input type="text" name="srollnum" class="form-control" id="inputRollNum" aria-describedby="emailHelp" placeholder="Enter Student Roll Number">     
  </div>
  
  <center><button type="submit" class="btn btn-primary">Save</button></center>
</form>


</div>
</body>
</html>