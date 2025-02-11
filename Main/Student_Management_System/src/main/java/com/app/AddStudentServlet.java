package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.util.JDBCConnector;

@WebServlet(urlPatterns = "/add")
public class AddStudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get");
		RequestDispatcher rd = req.getRequestDispatcher("add-form.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 
		Connection connection = null;
		PreparedStatement prestatement=null;
		
		String name = req.getParameter("sname");
		double marks = Double.parseDouble(req.getParameter("smarks"));
		int rollNum = Integer.parseInt(req.getParameter("srollnum"));
		

		
		try {
			connection = JDBCConnector.getConnection();
			String query = "insert into student(name , marks , rollNum) values (? , ? , ?)";
		     prestatement = connection.prepareStatement(query);
			prestatement.setString(1, name);
			prestatement.setDouble(2, marks);
			prestatement.setInt(3, rollNum);

			int row = prestatement.executeUpdate();
			if (row > 0) {
				System.out.println("data inserted !!!");
			} else {
				System.out.println("Somting went wrong data is not inserted !!!");
			}
			resp.sendRedirect("list");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("There is problem while add data to the database");
			e.printStackTrace();
		}finally
		{
			if(prestatement != null) {
				try {
					prestatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	
		
		

	
	
	}
	
	

	}

