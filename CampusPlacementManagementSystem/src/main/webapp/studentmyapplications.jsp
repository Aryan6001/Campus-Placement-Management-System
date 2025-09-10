<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="com.alien.entities.Application" %>
<%@ include file="studentdashboard.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<div class="container" style="margin-top:100px; ">
    
    <h2 class="text-center mb-5 text-primary">My Applications</h2>
    
    <table class="table  table-hover table-bordered text-center">
    <thead>
    <tr class="table-primary ">
      
      
      <th scope="col">Title</th>
      <th scope="col">Company</th>
      <th scope="col">Status</th>
      
    </tr>
  </thead>
  
  
  <tbody>
 <%
 
 List<Application> L=(List<Application>) request.getAttribute("joblist");
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
 	for(Application J : L)
 	{
 		
		String title=J.getTitle();
		String company=J.getCompany();
		String status=J.getStatus();
		
		
		 
%>
 	<tr >          
      <td><%=title%></td>
      <td><%=company %></td>
      <td style="color:red;"><%=status %></td>
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