package com.alien.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.alien.entities.StudentApplications;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AdminApplications")
public class AdminApplications extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        final String DB_URL = "jdbc:postgresql://localhost/placements";
        final String DB_USER = "placement";
        final String DB_PASS = "root@123";
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("adminlogin.jsp");
            return;
        }

        List<StudentApplications> applications = new LinkedList<>();

        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(
                "SELECT a.id, u.full_name, u.email, s.roll_no, s.branch, j.title, j.company, a.status, a.applied_at " +
                "FROM applications a " +
                "JOIN users u ON a.student_id=u.id " +
                "JOIN students s ON s.user_id=u.id " +
                "JOIN jobs j ON a.job_id=j.id"
            );

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("full_name");
                String email = rs.getString("email");
                String rollno = rs.getString("roll_no");
                String branch = rs.getString("branch");
                String title = rs.getString("title");
                String company = rs.getString("company");
                String status = rs.getString("status");

                StudentApplications sa = new StudentApplications(id, name, email, rollno, branch, title, company, status);
                applications.add(sa);
            }
        } catch (Exception e) {
            throw new ServletException("Error fetching applications", e);
        }

        request.setAttribute("applications", applications);
        RequestDispatcher rd = request.getRequestDispatcher("adminapplications.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
