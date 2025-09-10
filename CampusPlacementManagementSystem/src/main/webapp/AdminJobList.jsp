<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, com.alien.entities.*" %>
<%@ include file="admindashboard.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delete Student</title>
   
    
    <script type="text/javascript">
    
    		function del(srno)
    		{
    			var status = confirm("Do you want to delete Job for Job ID : " + srno);
    			
    			if(status==true)
    			{
    				// call a DeleteServlet with rno as trno=101 with REQUEST_METHOD = POST
    				// collect status[success, failed] from servlet
    				
    				// suceess --> show delete message
    				// failed  --> failure message
    				
    				fetch("http://localhost:8080/CampusPlacementManagementSystem/AdminJobList", 
    						{
    							method :'POST',
    							body   : new URLSearchParams({'trno': srno})
    						}
    				)
    				.then(response => response.text())
    				.then(data  => 
    							{
    								if(data.trim() == "success")
    								{
    									alert("Record is Deleted for Job ID :" + srno);
    									
    									var tr = document.getElementById(srno);
    				    					tr.remove();
    								}
    								if(data.trim()=="failed")
    								{
    									alert("Failed to Delete Record for Job ID :" + srno);
    								}
    							}
    				)
    				.catch( error => console.error("MyError while Deleting Job ID =" + srno));
    					
    			}
    			else
    			{
    				alert("Delete Skipped !!");
    			}
    		}
    	
    </script>
    
    
    
    
  </head>
<body>

    <div class="container" style="margin-top:100px; ">
    
    <h2 class="text-center mb-5 text-primary">Job Applications</h2>
    <div class="d-flex justify-content-end">
	
	<form class="d-flex  mb-4" role="search" method="GET" action="./AdminJobList">
		<input class="form-control me-3" type="search" name="srno" placeholder="Search Here">
		<input class="btn btn-outline-success me-3" type="submit" name="sbtn" value="Search">
		<input class="btn btn-outline-success" type="submit" name="sbtn" value="Refresh">		
	</form>
</div>
    <table class="table  table-hover table-bordered text-center">
    <thead>
    <tr class="table-primary ">
      
      <th scope="col">ID</th>
      <th scope="col">Title</th>
      <th scope="col">Company</th>
      <th scope="col">Location</th>
      <th scope="col">CTC(in Lakhs)</th>
      <th scope="col">Eligibility CGPA</th>
      <th scope="col">Description</th>
      <th scope="col">Delete Application</th>
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
 	<tr >
      
      <td><%=id %></td>
      <td><%=title%></td>
      <td><%=company %></td>
      <td><%=location %></td>
      <td><%=ctc %></td>
      <td><%=cgpa %></td>
      <td><%=description %></td>
      <td><button type="button" class="btn btn-danger" onclick="del(<%= id  %>)" >Delete</button></td>
    </tr>
<% 
 	}
 }
 %>
 </tbody>
 </table>
 
 
 
 
 </div>  <!-- end of container tag -->



</body>
</html>