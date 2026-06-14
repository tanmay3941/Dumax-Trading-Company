package com.dumax;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read form parameters
				String name = request.getParameter("uname");
				String email = request.getParameter("uemail");
				String phone = request.getParameter("uphone");
				String password = request.getParameter("upassword");
				String confirmPassword = request.getParameter("uconfirm");
				
				
				
				String url = "jdbc:mysql://localhost:3306/Dumax";
				String user = "root";
				String pass = "tanmay3941";
				
				try
				{
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con= DriverManager.getConnection(url,user,pass);
					Statement stmt= con.createStatement();
					stmt.executeUpdate(
						    "insert into users(name,email,mobile,password) values('"
						    + name + "','" + email + "','" + phone + "','" + password + "')"
						);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				RequestDispatcher rd = request.getRequestDispatcher("login page.html");
				rd.forward(request, response);
	}

}