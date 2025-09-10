<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student App</title>
  </head>
  <body>
   
    <div class="container" style="margin-top:100px; width:500px;">
    <h2 class="text-center mb-5" >Student Login</h2>
    
     <form method="post" action="./student-login">
    <div class="mb-3">
      <label>Email</label>
      <input class="form-control" type="email" name="email" required>
    </div>
    <div class="mb-3">
      <label>Password</label>
      <input class="form-control" type="password" name="password" required>
    </div>
    <button class="btn btn-primary" type="submit">Sign in</button>
  </form>
  <p class="mt-3"><a href="./register">New student? Register</a></p>
	
	</div>
	
	<p style="color:red;"> ${msg} </p>
	
  </body>
</html>