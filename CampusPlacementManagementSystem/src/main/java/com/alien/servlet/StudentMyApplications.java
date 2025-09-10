package com.alien.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.alien.entities.Application;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StudentMyApplications")
public class StudentMyApplications extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        final String DB_URL = "jdbc:postgresql://localhost/placements";
        final String DB_USER = "placement";
        final String DB_PASS = "root@123";
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"student".equals(session.getAttribute("role"))) {
            response.sendRedirect("studentlogin.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        List<Application> applications = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT j.id, j.title, j.company, a.status " +
                     "FROM applications a " +
                     "JOIN jobs j ON a.job_id = j.id " +
                     "WHERE a.student_id=? ")) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String title = rs.getString("title");
                String company = rs.getString("company");
                String status = rs.getString("status");
                

                Application app = new Application(title, company, status);
                applications.add(app);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        request.setAttribute("joblist", applications);
        RequestDispatcher rd = request.getRequestDispatcher("studentmyapplications.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Reuse doGet logic
    }
}
