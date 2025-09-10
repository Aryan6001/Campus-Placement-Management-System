package com.alien.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class UpdateStatus
 */
@WebServlet("/UpdateStatus")
public class UpdateStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection getConnection() throws Exception {
   	 Class.forName("org.postgresql.Driver");
        
		
		final String DB_URL="jdbc:postgresql://localhost/placements";
		final String DB_USER ="placement";
		final String DB_PASS="root@123"; 

        
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); 
        }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }
        String appId = request.getParameter("appId");
        String status = request.getParameter("status");
        String message=null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement("UPDATE applications SET status=? WHERE id=?")) {
            ps.setString(1, status);
            ps.setInt(2, Integer.parseInt(appId));
            ps.executeUpdate();
            message="<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">Status Updated!!!</div>"; 

        } catch(Exception e){
            throw new ServletException(e);
        }
        request.setAttribute("msg",message);
		RequestDispatcher rd =request.getRequestDispatcher("adminapplications.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
