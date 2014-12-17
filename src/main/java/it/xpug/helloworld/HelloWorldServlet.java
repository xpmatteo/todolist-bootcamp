package it.xpug.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = -4196202522268353783L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ToDoList toDoList = ToDoList.newDefaultToDoList();
		
		ToDoListJsonSerializer serializer = new ToDoListJsonSerializer(toDoList);
		
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(serializer.toString());
		writer.close();
	}
}
