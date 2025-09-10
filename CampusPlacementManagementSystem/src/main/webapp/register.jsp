<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>Student Registration</title>
  
</head>
<body class="container py-4">
<div class="container" style="margin-top:100px; width:900px;">
  <h3>Student Registration</h3>
  <form method="post" action="./register">
    <div class="row g-3">
      <div class="col-md-6">
        <label>Full Name</label>
        <input class="form-control" type="text" name="fullName" required>
      </div>
      <div class="col-md-6">
        <label>Email</label>
        <input class="form-control" type="email" name="email" required>
      </div>
      <div class="col-md-6">
        <label>Password</label>
        <input class="form-control" type="password" name="password" required>
      </div>
      <div class="col-md-6">
        <label>Roll No</label>
        <input class="form-control" type="text" name="rollNo" required>
      </div>
      <div class="col-md-6">
        <label>Branch</label>
        <input class="form-control" type="text" name="branch" required>
      </div>
      <div class="col-md-6">
        <label>CGPA</label>
        <input class="form-control" type="number" step="0.01" min="0" max="10" name="cgpa" required>
      </div>
    </div>
    <button class="btn btn-success mt-3" type="submit">Register</button>
  </form>
  <p style="color:red;"> ${msg} </p>
 </div>
</body>
</html>
