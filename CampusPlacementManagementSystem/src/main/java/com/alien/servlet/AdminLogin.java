package com.alien.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentLogin
 */
@WebServlet({ "/AdminLogin", "/admin-login" })
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection getConnection() throws Exception {
   	 Class.forName("org.postgresql.Driver");	
		final String DB_URL="jdbc:postgresql://localhost/placements";
		final String DB_USER ="placement";
		final String DB_PASS="root@123"; 
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd =request.getRequestDispatcher("adminlogin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message=null;
        Connection con = null;
		try {
			con = getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try  {
        	
            PreparedStatement ps = con.prepareStatement("SELECT id, role, full_name FROM users WHERE email=? AND password_hash=?");
        	
        	ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
               if (rs.next()) {
                   HttpSession session = request.getSession(true);
                   session.setAttribute("userId", rs.getInt("id"));
                   session.setAttribute("role", rs.getString("role"));
                   session.setAttribute("fullName", rs.getString("full_name"));
                   if ("admin".equals(rs.getString("role"))) {
                       response.sendRedirect("AdminHome.jsp");
                       return;
                   }
                   else
                   {
                	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Login Failed!!!</div>";                   
                   }
               }
               else 
               {
            	   request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
            	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Login Failed!!!</div>"; 
            	   return;
               }
           }
        catch (Exception e) 
        {
        	 throw new ServletException(e);
        }
        finally
		{
			try {
				con.close();
			} catch (SQLException e) {	
			}			
		}
        
        request.setAttribute("msg",message);
		RequestDispatcher rd =request.getRequestDispatcher("adminlogin.jsp");
		rd.forward(request, response);

	}

}