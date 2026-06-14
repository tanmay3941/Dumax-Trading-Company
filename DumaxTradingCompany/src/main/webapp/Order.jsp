<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.*" %>
<%
    HttpSession sessionObj = request.getSession(false);

    if(sessionObj == null || sessionObj.getAttribute("userName") == null){
        response.sendRedirect("login page.html");
        return;
    }

    String userName = (String) sessionObj.getAttribute("userName");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Orders - Dumax Paints</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(to right, #e0f2ff, #f8fbff);
            margin: 0;
            padding: 0;
        }

        .container {
            width: 85%;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #1e3a8a;
            margin-bottom: 30px;
        }

        .welcome {
            text-align: right;
            color: #444;
            margin-bottom: 15px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            overflow: hidden;
            border-radius: 10px;
        }

        th {
            background: linear-gradient(135deg, #2563eb, #3b82f6);
            color: white;
            padding: 12px;
            text-align: center;
        }

        td {
            padding: 12px;
            text-align: center;
            background-color: #f9fbff;
            border-bottom: 1px solid #dbeafe;
        }

        tr:hover td {
            background-color: #e0f2ff;
            transition: 0.3s;
        }

        .no-order {
            text-align: center;
            padding: 20px;
            color: #777;
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background: #10b981;
            color: white;
            border-radius: 25px;
            text-decoration: none;
            transition: 0.3s;
        }

        .btn-back:hover {
            background: #059669;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="welcome">
        Welcome, <b><%= userName %></b>
    </div>

    <h2>My Paint Orders</h2>

    <table>
        <tr>
            <th>Paint Type</th>
            <th>Colours</th>
            <th>Add-ons</th>
            <th>Order Date</th>
        </tr>

<%
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dumax_db",
            "root",
            "tanmay3941"
        );

        PreparedStatement ps = con.prepareStatement(
            "SELECT paint_type, colours, addons, order_date FROM orders WHERE username = ?"
        );

        ps.setString(1, userName);

        ResultSet rs = ps.executeQuery();

        boolean hasOrders = false;

        while(rs.next()){
            hasOrders = true;
%>
        <tr>
            <td><%= rs.getString("paint_type") %></td>
            <td><%= rs.getString("colours") %></td>
            <td><%= rs.getString("addons") %></td>
            <td><%= rs.getString("order_date") %></td>
        </tr>
<%
        }

        if(!hasOrders){
%>
        <tr>
            <td colspan="4" class="no-order">No orders placed yet</td>
        </tr>
<%
        }

        con.close();

    } catch(Exception e){
        out.println("<tr><td colspan='4'>Error loading orders</td></tr>");
        e.printStackTrace();
    }
%>

    </table>

    <div style="text-align:center;">
        <a href="index.jsp" class="btn-back">Back to Home</a>
    </div>
</div>

</body>
</html>