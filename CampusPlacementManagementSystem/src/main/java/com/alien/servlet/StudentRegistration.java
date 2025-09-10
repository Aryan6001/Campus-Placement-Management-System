package com.alien.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class StudentRegistration
 */
@WebServlet({ "/StudentRegistration", "/register" })
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection getConnection() throws Exception {
	   	 	Class.forName("org.postgresql.Driver");	
			final String DB_URL="jdbc:postgresql://localhost/placements";
			final String DB_USER ="placement";
			final String DB_PASS="root@123"; 
	        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd =request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rollNo = request.getParameter("rollNo");
        String branch = request.getParameter("branch");
        String cgpaStr = request.getParameter("cgpa");
        String message=null;
        try {
            double cgpa = Double.parseDouble(cgpaStr);
            try (Connection con = getConnection()) {
                con.setAutoCommit(false);
                try (PreparedStatement ps1 = con.prepareStatement("INSERT INTO users(role, full_name, email, password_hash) VALUES('student',?,?,?) RETURNING id");
                     PreparedStatement ps2 = con.prepareStatement("INSERT INTO students(user_id, roll_no, branch, cgpa) VALUES(?,?,?,?)")) {
                    ps1.setString(1, fullName);
                    ps1.setString(2, email);
                    ps1.setString(3, password);
                    ResultSet rs = ps1.executeQuery();
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        ps2.setInt(1, userId);
                        ps2.setString(2, rollNo);
                        ps2.setString(3, branch);
                        ps2.setDouble(4, cgpa);
                        ps2.executeUpdate();
                  	   message="<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">Registration Successfully!!!</div>";                   

                        con.commit();
                       
                    } else {
                        con.rollback();
                 	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Failed to register!!!</div>";                   

                    }
                } catch (Exception e) {
                    con.rollback();
                    throw e;
                } finally {
                    con.setAutoCommit(true);
                }
            }
        } catch (Exception e) {
     	    message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Please Enter Valid Data!!!</div>";                   
            throw new ServletException(e);
        }
        request.setAttribute("msg",message);
        request.getRequestDispatcher("register.jsp").forward(request, response);
    
	}

}
