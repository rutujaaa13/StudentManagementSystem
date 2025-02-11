package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entity.Student;
import com.jdbc.util.JDBCConnector;

@WebServlet(urlPatterns = "/edit")

public class EditStudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sid = Integer.parseInt(req.getParameter("id"));
		
		Connection connection=null;
		PreparedStatement statement=null;
		Student s=null;
		
		List<Student> data = new ArrayList<>();
		try {
			connection = JDBCConnector.getConnection();

			String query = "select * from student where id=?" ;
			statement = connection.prepareStatement(query) ;
			statement.setInt(1, sid);
			ResultSet resultSet = statement.executeQuery() ;
		
			
			
			while( resultSet.next() ) {
				int id = resultSet.getInt("id") ;
				String name = resultSet.getString("name");
				double marks = resultSet.getDouble("marks");
				int rollNum = resultSet.getInt("rollNum") ;
				 s = new Student(id, name, marks,rollNum);
				
			}
			System.out.println(s);
		
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		} finally
		{
			try {
				statement.close() ;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		if(data != null)
		{
			req.setAttribute("obj", s);
		}
		RequestDispatcher rd = req.getRequestDispatcher("edit-student.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int  sid = Integer.parseInt(req.getParameter("sid"));
		String name = req.getParameter("sname");
		double marks = Double.parseDouble(req.getParameter("smarks"));
		int rollNum = Integer.parseInt(req.getParameter("srollnum"));
		System.out.println(name);
		System.out.println(marks);
		System.out.println(rollNum);
		
		
		
		try {
			Connection connection = JDBCConnector.getConnection();
			String query = "update student set name=?, marks=?,rollNum=? where id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query) ;
			preparedStatement.setString(1, name);
			preparedStatement.setDouble(2, marks);
			preparedStatement.setInt(3, rollNum);
			preparedStatement.setInt(4, sid);
			
			int row = preparedStatement.executeUpdate() ;
			if(row > 0) {
				System.out.println("data updated !!!");
			} else {
				System.out.println("Somting went wrong data is not updated !!!");
			}
			
			preparedStatement.close();
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		resp.sendRedirect("list");
		
	

	}
	
	
	

}
