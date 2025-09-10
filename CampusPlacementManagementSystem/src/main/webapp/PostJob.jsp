<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ include file="admindashboard.jsp" %>
<%
  if (session.getAttribute("userId") == null || !"admin".equals(session.getAttribute("role"))) {
    response.sendRedirect("adminlogin.jsp");
    return;
  }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Post Job</title>

</head>
<body class="container py-4">
 <div class="container" style="margin-top:100px; width:500px;">
 <p style="color:red;"> ${msg} </p>
  <h3>Post a Job</h3>
    
  
  <form method="post" action="./post-job">
    <div class="mb-2"><label>Title</label><input class="form-control" name="title" required></div>
    <div class="mb-2"><label>Company</label><input class="form-control" name="company" required></div>
    <div class="mb-2"><label>Location</label><input class="form-control" name="location" required></div>
    <div class="mb-2"><label>CTC</label><input class="form-control" name="ctc"></div>
    <div class="mb-2"><label>Eligibility CGPA</label><input class="form-control" name="eligibilityCgpa" type="number" step="0.01" value="0"></div>
    <div class="mb-2"><label>Description</label><textarea class="form-control" name="description" rows="5" required></textarea></div>
    <button class="btn btn-success" type="submit">Publish</button>
  </form>
  
  
  </div>
</body>
</html>