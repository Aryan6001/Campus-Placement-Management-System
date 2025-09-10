<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.alien.entities.StudentApplications" %>
<%@ include file="admindashboard.jsp" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Job Applications</title>
</head>
<body>

<div class="container" style="margin-top:100px;">

    <h2 class="text-center mb-5 text-primary">Job Applications</h2>
    
    <div class="d-flex justify-content-end">
        <form class="d-flex mb-4" role="search" method="GET" action="./AdminApplications">
            <input class="form-control me-3" type="search" name="srno" placeholder="Enter Company Name">
            <input class="btn btn-outline-success me-3" type="submit" name="sbtn" value="Search">
            <input class="btn btn-outline-success" type="submit" name="sbtn" value="Refresh">        
        </form>
    </div>

    <c:if test="${not empty msg}">
        ${msg}
    </c:if>

    <table class="table table-hover table-bordered text-center">
        <thead>
            <tr class="table-primary">
                <th scope="col">ID</th>
                <th scope="col">Name</th>
                <th scope="col">Email</th>
                <th scope="col">Roll No</th>
                <th scope="col">Branch</th>
                <th scope="col">Title</th>
                <th scope="col">Company</th>
                <th scope="col">Status</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<StudentApplications> L = (List<StudentApplications>) request.getAttribute("applications");
            if (L == null || L.isEmpty()) {
        %>
            <tr>
                <td colspan="8">No Data Found !!!</td>
            </tr>
        <%
            } else {
                for (StudentApplications rs : L) {
                    int id = rs.getId();
                    String name = rs.getName();
                    String email = rs.getEmail();
                    String rollno = rs.getRollno();
                    String branch = rs.getBranch();
                    String title = rs.getTitle();
                    String company = rs.getCompany();
                    String status = rs.getStatus();
        %>
            <tr>
                <td><%=id%></td>
                <td><%=name%></td>
                <td><%=email%></td>
                <td><%=rollno%></td>
                <td><%=branch%></td>
                <td><%=title%></td>
                <td><%=company%></td>
                <td>
                    <form method="post" action="./UpdateStatus" class="d-flex gap-2">
                        <input type="hidden" name="appId" value="<%=id%>">
                        <select name="status" class="form-select form-select-sm">
                            <option <%= "applied".equals(status) ? "selected" : "" %>>applied</option>
                            <option <%= "shortlisted".equals(status) ? "selected" : "" %>>shortlisted</option>
                            <option <%= "rejected".equals(status) ? "selected" : "" %>>rejected</option>
                            <option <%= "selected".equals(status) ? "selected" : "" %>>selected</option>
                        </select>
                        <button class="btn btn-sm btn-primary" type="submit">Update</button>
                    </form>
                </td>
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
