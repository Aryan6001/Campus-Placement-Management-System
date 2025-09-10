<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.alien.entities.Job" %>
<%@ include file="studentdashboard.jsp" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
  
  </head>
  <body>
 	
    <div class="container" style="margin-top:100px; ">
    
    <h2 class="text-center mb-5 text-primary">Job Applications</h2>
    <div class="d-flex justify-content-end">
	
	<form class="d-flex  mb-4" role="search" method="GET" action="./JobApplications">
		<input class="form-control me-3" type="search" name="srno" placeholder="Enter Company Name">
		<input class="btn btn-outline-success me-3" type="submit" name="sbtn" value="Search">
		<input class="btn btn-outline-success" type="submit" name="sbtn" value="Refresh">		
	</form>
</div>
	<c:if test="${not empty msg}">
    ${msg}
</c:if>
    <table class="table  table-hover table-bordered text-center" >
    <thead>
    <tr class="table-primary ">
      <th scope="col">Job Id</th>
      
      <th scope="col">Title</th>
      <th scope="col">Company</th>
      <th scope="col">Location</th>
      <th scope="col">CTC(in Lakhs)</th>
      <th scope="col">Eligibility CGPA</th>
      <th scope="col">Description</th>
      <th scope="col">Apply</th>
    </tr>
  </thead>
  
  
  <tbody>
 <%
 
 List<Job> L=(List<Job>) request.getAttribute("joblist");
 if(L==null || L.isEmpty())
 {
%>
	<tr>
	<td colspan="8">
		No Data Found !!!
	</td>
	</tr>

<% 	 
 }
 else{
 	for(Job J : L)
 	{
 		int id=J.getId();
		String title=J.getTitle();
		String company=J.getCompany();
		String location=J.getLocation();
		int ctc=J.getCtc();
		double cgpa=J.getCgpa();
		String description=J.getDescription();
		
		 
%>
 	<tr>
      
      <td><%=id%></td>
      <td><%=title%></td>
      <td><%=company %></td>
      <td><%=location %></td>
      <td><%=ctc %></td>
      <td><%=cgpa %></td>
      <td><%=description %></td>
      <td><a class="btn btn-sm btn-outline-success" href="./ApplyJob?jobId=<%=J.getId() %>">Apply</a></td>
    </tr>
<% 
 	}
 }
 %>
 </tbody>
 </table>
 
 
 
 
 </div>
 
  </body>
</html>