package com.dumax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


public class CheckSession extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // ✅ If user logged in
        if (session != null && session.getAttribute("userEmail") != null) {

            response.sendRedirect("Visualizer.html"); // protected page
        }

        // ❌ If NOT logged in
        else {

            response.setContentType("text/html");

            response.getWriter().println(
                "<script>" +
                "alert('⚠ Please login to access this page');" +
                "window.location='login page.html';" +
                "</script>"
            );
        }
    }
}