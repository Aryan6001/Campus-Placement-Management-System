package com.alien.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.alien.entities.Job;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JobApplications
 */
@WebServlet("/JobApplications")
public class JobApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection getConnection() throws Exception {
   	 Class.forName("org.postgresql.Driver");
        
		
		final String DB_URL="jdbc:postgresql://localhost/placements";
		final String DB_USER ="placement";
		final String DB_PASS="root@123";        
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String qry="";
		
		String sbtn = request.getParameter("sbtn");
		String srno = request.getParameter("srno");  // String srno="";
		
		if(sbtn==null  || srno.isEmpty() || sbtn.equals("Refresh"))
		{		
			qry = "SELECT * FROM jobs";
		}
		else if(sbtn.equals("Search"))
		{
			
			qry = "SELECT * FROM jobs WHERE company='"+srno+"'";
		}
		
		List<Job> L =new LinkedList<>();

		try
		{
			
			con=getConnection();
			ps=con.prepareStatement(qry);
			

			rs=ps.executeQuery();
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
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();	
			
		}
		finally
		{
			try {
				con.close();
				rs.close();
				//out.close();
			} catch (Exception e) {
				
				//e.printStackTrace();
				
			}
		}
		request.setAttribute("joblist", L);
		RequestDispatcher rd=request.getRequestDispatcher("jobapplications.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String qry="select * from jobs";
		
		List<Job> L =new LinkedList<>();

		try
		{
			
			con=getConnection();
			ps=con.prepareStatement(qry);
			

			rs=ps.executeQuery();
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
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();	
			
		}
		finally
		{
			try {
				con.close();
				rs.close();
				//out.close();
			} catch (Exception e) {
				
				//e.printStackTrace();
				
			}
		}
		request.setAttribute("joblist", L);
		RequestDispatcher rd=request.getRequestDispatcher("jobapplications.jsp");
		rd.forward(request, response);
	}

}
