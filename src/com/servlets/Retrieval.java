package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBConnection;

@WebServlet("/Retrieval")
public class Retrieval extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String productId = request.getParameter("product_id");
		int total = Integer.parseInt(request.getParameter("total"));
		PrintWriter out = response.getWriter();
		

		// DB here
		String resultSQL = "Select id, name, category, price from product where id=?";

		if (Integer.parseInt(productId) <= total) {

			try {
				InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("configr.properties");
				Properties props = new Properties();
				props.load(in);
				DBConnection conn = new DBConnection(props.getProperty("dbURL"), props.getProperty("user"),
						props.getProperty("pwd"));

				PreparedStatement preparedStatemnt = conn.getConnection().prepareStatement(resultSQL);
				preparedStatemnt.setInt(1, Integer.parseInt(productId));

				ResultSet rs = preparedStatemnt.executeQuery();
				out.println("<html><head>");
				out.println("<title>Product Details</title>");
				out.println("</head><body>");
				out.println("<table border='1' width='60%'>");
				out.println("<tr>");
				out.println("<th>Model Id</th> <th>Product Name</th> <th>Category</th> <th>Price</th> </tr>");

				while (rs.next()) {
					int id = rs.getInt(1);
					String name = rs.getString(2);
					String category = rs.getString(3);
					double price = rs.getDouble(4);
					out.println("<tr style='text-align:center'>");
					out.println("<td>" + id + "</td> <td>" + name + "</td> <td>" + category + "</td> <td>" + price
							+ "</td>");
					out.println("</tr>");

				}

				out.println("</table>");
				out.println("<a href='index.jsp'>Go Back to Home Page</a>");
				out.println("</body></html>");

				preparedStatemnt.close();
				conn.closeConnection();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else {
			out.println("<html><head>");
			out.println("<title>404</title>");
			out.println("</head><body>");
			out.println("<h3 style='color:red'>Please select a valid Model Id</h3>");
			out.println("<p style='color:red';>Model Id " + productId + " does not exist!</h3><br>");
			out.println("<a style='margin-top:80px' href='index.jsp'>Go Back to Home Page</a>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
