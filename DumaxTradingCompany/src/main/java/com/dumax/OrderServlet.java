package com.dumax;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import com.google.gson.*;

public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userName") == null) {
            response.sendRedirect("login page.html");
            return;
        }

        String userName = (String) session.getAttribute("userName");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        Gson gson = new Gson();
        OrderData data = gson.fromJson(sb.toString(), OrderData.class);

        String paintType = data.paintType;
        String colours = String.join(", ", data.colours);
        String addons = String.join(", ", data.addons);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dumax_db",
                "root",
                "tanmay3941"
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO orders(username, paint_type, colours, addons) VALUES (?, ?, ?, ?)"
            );

            ps.setString(1, userName); 
            ps.setString(2, paintType);
            ps.setString(3, colours);
            ps.setString(4, addons);

            ps.executeUpdate();
            con.close();

            response.getWriter().write("Order Saved Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error Saving Order");
        }
    }
}