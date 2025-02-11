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

@WebServlet(urlPatterns = "/delete")
public class DeleteStudentServlet extends HttpServlet {

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
		RequestDispatcher rd = req.getRequestDispatcher("delete.jsp");
		rd.forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sid = Integer.parseInt(req.getParameter("id"));
		
		try {
			Connection connection = JDBCConnector.getConnection();
			
			PreparedStatement preparedStatement = connection.prepareStatement("delete from student where id=?");
			preparedStatement.setInt(1, sid);
			
			
			

			int row = preparedStatement.executeUpdate() ;
			if(row > 0) {
				System.out.println("data deleted !!!");
			} else {
				System.out.println("Somting went wrong data is not deleted !!!");
			}
			
			preparedStatement.close() ;
			
		} catch (Exception e) {
			System.out.println("there is problem in deleting");
		}
		resp.sendRedirect("list");
	}

}
