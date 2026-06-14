package com.dumax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DLog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String lemail=request.getParameter("lemail");
		String lpass= request.getParameter("lpassword");
		
		
		String url="jdbc:mysql://localhost:3306/Dumax",
				user="root",
				pass="tanmay3941";
		
		//  CHECK ADMIN
		if(lemail.equals("admin@gmail.com") && lpass.equals("admin@123")) {

			HttpSession session = request.getSession();
		    session.setAttribute("adminEmail", lemail);
		    response.sendRedirect("admin.jsp");//create this
		    return;   // stops further execution
		}
		
		
		// CHECK USER
		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection(url,user,pass);
			Statement stmt= con.createStatement();
			ResultSet rs = stmt.executeQuery(
				    "select * from users where email='" + lemail +
				    "' and password='" + lpass + "'"
				);
			
				if(rs.next())
				{
					HttpSession session = request.getSession();
				    session.setAttribute("userEmail", lemail);
				    ResultSet us = stmt.executeQuery("select name from users where email='"+lemail+"'");
				    if(us.next()) {
				        String Sname = us.getString("name");  // Store the name in string variable
				        session.setAttribute("userName", Sname);
				    }
			
					//sending request to home 
					response.sendRedirect("index.jsp");//FOR TANMAY change this after wards 
				}
				else {
					response.setContentType("text/html");
					PrintWriter out = response.getWriter();

				out.println("<div id='errorToast' style='"
				        + "position:fixed;"
				        + "top:20px;"                    // Top positioning
				        + "left:20px;"                   // Left side
				        + "max-width:400px;"             // Match form width
				        + "padding:18px 22px;"
				        + "background:#ffe6e6;"
				        + "border:1px solid #ff4d4d;"
				        + "border-radius:12px;"          // Rounded corners
				        + "box-shadow:0 8px 25px rgba(0,0,0,0.25);"
				        + "font-family:Poppins,Arial,sans-serif;"  // Match page font
				        + "color:#cc0000;"
				        + "z-index:1000;"                // Above everything
				        + "transform:translateX(-100%);" // Start hidden off-screen
				        + "transition:transform 0.4s ease, opacity 0.4s ease;"
				        + "opacity:0;"
				        + "display:flex;"
				        + "align-items:center;"
				        + "gap:12px;'>"
				        + "<svg width='20' height='20' viewBox='0 0 24 24' fill='currentColor'>"
				        + "<path d='M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z'/>"
				        + "</svg>"
				        + "<div>"
				        + "<h3 style='margin:0 0 4px 0; font-size:16px; font-weight:600;'>Invalid Credentials</h3>"
				        + "<p style='margin:0; font-size:14px; opacity:0.9;'>Create an account if you haven\\'t registered yet.</p>"
				        + "</div>"
				        + "</div>");

				out.println("<script>"
				        + "setTimeout(() => {"
				        + "  const toast = document.getElementById('errorToast');"
				        + "  toast.style.transform = 'translateX(0)';"
				        + "  toast.style.opacity = '1';"
				        + "}, 200);"
				        + "setTimeout(() => {"
				        + "  const toast = document.getElementById('errorToast');"
				        + "  toast.style.transform = 'translateX(-100%)';"
				        + "  toast.style.opacity = '0';"
				        + "  setTimeout(() => toast.remove(), 400);"
				        + "}, 5000);"
				        + "</script>");

				RequestDispatcher rd = request.getRequestDispatcher("registration page.html");
				rd.include(request, response);


			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
