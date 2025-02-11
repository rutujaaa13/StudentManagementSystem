package com.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

@WebServlet(urlPatterns = "/list")

public class StudentListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connection connection=null;
		Statement statement=null;
		
		List<Student> data = new ArrayList<>();
		try {
			connection = JDBCConnector.getConnection();
			statement = connection.createStatement() ;
		

			String query = "select * from student" ;
			ResultSet resultSet = statement.executeQuery(query) ;
			
			while( resultSet.next() ) {
				int id = resultSet.getInt("id") ;
				String name = resultSet.getString("name");
				double marks = resultSet.getDouble("marks");
				int rollNum = resultSet.getInt("rollNum") ;
				Student s = new Student(id, name, marks,rollNum);
				data.add(s);
			}
			System.out.println(data);
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			try {
				statement.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(data != null)
		{
			req.setAttribute("data", data);
		}
		
	RequestDispatcher rd = req.getRequestDispatcher("student-list.jsp");
	rd.forward(req, resp);
	
	}
	
	

}
