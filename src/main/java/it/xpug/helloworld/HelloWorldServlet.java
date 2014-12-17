package it.xpug.helloworld;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = -4196202522268353783L;
	private static final ToDoList toDoList = ToDoList.newDefaultToDoList();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		getToDoList(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		addToDo(request, response);
	}

	private void getToDoList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ToDoListJsonSerializer serializer = new ToDoListJsonSerializer(toDoList);

		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(serializer.serialize());
		writer.close();
	}

	private void addToDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringWriter writer = new StringWriter();

		try {
			IOUtils.copy(request.getReader(), writer);
		}
		catch (IOException e) {}

		String json = String.format("{\"text\":\"%s\"}", writer.toString());
		toDoList.addToDo(new ToDoJsonSerializer(json).deserialize());

		getToDoList(request, response);
	}
}
