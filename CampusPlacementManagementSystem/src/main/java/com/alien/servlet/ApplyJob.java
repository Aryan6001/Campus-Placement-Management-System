package com.alien.servlet;

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
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.alien.entities.Job;

/**
 * Servlet implementation class ApplyJob
 */
@WebServlet("/ApplyJob")
public class ApplyJob extends HttpServlet {
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
        if (session == null || !"student".equals(session.getAttribute("role"))) {
            response.sendRedirect("studentlogin.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userId");
        String jobIdStr = request.getParameter("jobId");
        String message=null;
        try (Connection con = getConnection()) {
            // Check CGPA eligibility
        	if(jobIdStr==null)
        	{
         	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">No record is found!!!</div>";                   

        	}
        	else {
        		try (PreparedStatement ps = con.prepareStatement(
        				
        				"SELECT j.id, j.eligibility_cgpa, s.cgpa FROM jobs j, students s WHERE j.id=? AND s.user_id=?")) {
        			ps.setInt(1, Integer.parseInt(jobIdStr));
        			ps.setInt(2, userId);
                	ResultSet rs = ps.executeQuery();
                	if (rs.next()) {
                		double min = rs.getDouble("eligibility_cgpa");
                		double cgpa = rs.getDouble("cgpa");
                    	if (cgpa < min) {
                    		message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">You are not elligible!!!</div>";  
                    		request.setAttribute("msg",message);
                    		request.getRequestDispatcher("jobapplications.jsp").forward(request, response);
                    		return;
                    	}
                	}
        		}
        		try (PreparedStatement ps2 = con.prepareStatement("INSERT INTO applications(job_id, student_id) VALUES(?,?) ON CONFLICT DO NOTHING")) {
        			ps2.setInt(1, Integer.parseInt(jobIdStr));
        			ps2.setInt(2, userId);
        			ps2.executeUpdate();
        			message="<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">Applied Successfully!!!</div>";  
            		
        		}
        		try
        		{
        			List<Job> L =new LinkedList<>();
        		
        			Connection con2=getConnection();
        			PreparedStatement ps=con.prepareStatement("select * from jobs");
        			ResultSet rs=ps.executeQuery();

        			while(rs.next())
        			{
        				int id=rs.getInt("id");
        				String title=rs.getString("title");
        				String company=rs.getString("company");
        				String location=rs.getString("location");
        				int ctc=rs.getInt("ctc");
        				double cgpa=rs.getDouble("eligibility_cgpa");
        				String description=rs.getString("description");
        				Job J=new Job(id,title,company,location,ctc,cgpa,description);
        				L.add(J);
        			}
        		}finally
        		{
        			
        		}
        		
        	
        	
    		request.setAttribute("msg",message);
    		request.getRequestDispatcher("jobapplications.jsp").forward(request, response);
        }} catch(Exception e){
            throw new ServletException(e);
        }
    

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
        if (session == null || !"student".equals(session.getAttribute("role"))) {
            response.sendRedirect("studentlogin.jsp");
            return;
        }
        int userId = (Integer) session.getAttribute("userId");
        String jobIdStr = request.getParameter("jobId");
        String message=null;
        try (Connection con = getConnection()) {
            // Check CGPA eligibility
        	if(jobIdStr==null)
        	{
         	   message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">No record is found!!!</div>";                   

        	}
        	else {
        		try (PreparedStatement ps = con.prepareStatement(
        				
        				"SELECT j.id, j.eligibility_cgpa, s.cgpa FROM jobs j, students s WHERE j.id=? AND s.user_id=?")) {
        			ps.setInt(1, Integer.parseInt(jobIdStr));
        			ps.setInt(2, userId);
                	ResultSet rs = ps.executeQuery();
                	if (rs.next()) {
                		double min = rs.getDouble("eligibility_cgpa");
                		double cgpa = rs.getDouble("cgpa");
                    	if (cgpa < min) {
                    		message="<div class=\"alert alert-danger mt-3 text-center container\" role=\"alert\">You are not elligible!!!</div>";  
                    		request.setAttribute("msg",message);
                    		request.getRequestDispatcher("jobapplications.jsp").forward(request, response);
                    		return;
                    	}
                	}
        		}
        		try (PreparedStatement ps2 = con.prepareStatement("INSERT INTO applications(job_id, student_id) VALUES(?,?) ON CONFLICT DO NOTHING")) {
        			ps2.setInt(1, Integer.parseInt(jobIdStr));
        			ps2.setInt(2, userId);
        			ps2.executeUpdate();
        			message="<div class=\"alert alert-success mt-3 text-center container\" role=\"alert\">You are not elligible!!!</div>";  
            		
        		}
        		
        	
        		
        	}
        	
    		request.setAttribute("msg",message);
    		request.getRequestDispatcher("jobapplications.jsp").forward(request, response);
        } catch(Exception e){
            throw new ServletException(e);
        }
	}

}
