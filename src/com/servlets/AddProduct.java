package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.model.Product;
import com.util.HibernateUtil;

@WebServlet("/addproduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		double price = Double.valueOf(request.getParameter("price"));
		
		Product p = new Product(name, category, price);
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		PrintWriter out = response.getWriter();
		
		try {
			Serializable key = session.save(p);
			p = (Product) session.createQuery("from Product where id="+key).uniqueResult();
			System.out.println("product added");
			
			System.out.println(p);
			transaction.commit();
			
			
			out.println("<html><head>");
			out.println("<title>Product Detail</title>");
			out.println("<table border='1' width='60%'>");
			out.println("<tr>");
			out.println("<th>Model Id</th> <th>Product Name</th> <th>Category</th> <th>Price</th> </tr>");
			out.println("<tr style='text-align:center'>");
			out.println("<td>" + p.getId() + "</td> <td>" + p.getName() + "</td> <td>" + p.getCategory() + "</td> <td>" + p.getPrice()
					+ "</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<a href='index.jsp'>Go Back to Home Page</a> <br>");
			out.println("<a href='add_product.jsp'>Go Back to Add product Page</a>");
			out.println("</body></html>");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
