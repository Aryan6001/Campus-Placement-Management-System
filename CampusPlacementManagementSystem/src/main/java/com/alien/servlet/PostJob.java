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
 * Servlet implementation class PostJob
 */
@WebServlet({"/PostJob","/post-job"})
public class PostJob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection getConnection() throws Exception {
   	 Class.forName("org.postgresql.Driver");		
		final String DB_URL="jdbc:postgresql://localhost/placements";
		final String DB_USER ="placement";
		final String DB_PASS="root@123";       
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS); 
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd =request.getRequestDispatcher("PostJob.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("adminlogin.jsp");
            return;
        }
        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String location = request.getParameter("location");
        String ctc = request.getParameter("ctc");
        String description = request.getParameter("description");
        String eligibility = request.getParameter("eligibilityCgpa");
        String message =null;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO jobs(title, company, location, description, ctc, eligibility_cgpa, posted_by) VALUES(?,?,?,?,?,?,?)")) {
            ps.setString(1, title);
            ps.setString(2, company);
            ps.setString(3, location);
            ps.setString(4, description);
            ps.setString(5, (ctc==null||ctc.isEmpty())? null: ctc);
            ps.setDouble(6, eligibility==null||eligibility.isEmpty()? 0.0 : Double.parseDouble(eligibility));
            ps.setInt(7, (Integer) session.getAttribute("userId"));
            ps.executeUpdate();
      	   message="<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">Posted Successfully!!!</div>"; 

            
        } catch (Exception e) {
     	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">Please Enter Valid Data!!!</div>"; 
            throw new ServletException(e);
        }
        
		request.setAttribute("msg",message);
		RequestDispatcher rd =request.getRequestDispatcher("PostJob.jsp");
		rd.forward(request, response);
	}

}
