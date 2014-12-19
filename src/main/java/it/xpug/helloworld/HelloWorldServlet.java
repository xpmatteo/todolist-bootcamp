package it.xpug.helloworld;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

	private static final long serialVersionUID = -4196202522268353783L;
	private static final ToDoList toDoList = new ToDoList("Titolo della lista");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String[] uriParts = uri.split("/");
		if (isRootRequested(uriParts)) {
			redirectToIndex(response);
		}
		else if (isToDoListsRequested(uriParts)) {
			if (isToDoListIdRequested(uriParts)) {
				getToDoListAndWriteToResponse(new Long(uriParts[2]), response);
			}
		}
		else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addToDo(request, response);
	}

	private boolean isRootRequested(String[] uriParts) {
		return uriParts.length < 2;
	}

	private void redirectToIndex(HttpServletResponse response) throws IOException {
		response.sendRedirect("index.html");
	}

	private boolean isToDoListIdRequested(String[] uriParts) {
		return uriParts.length > 2;
	}

	private boolean isToDoListsRequested(String[] uriParts) {
		return "todolists".equals(uriParts[1]);
	}

	private void getToDoListAndWriteToResponse(long id, HttpServletResponse response) throws IOException {
		ToDoListJsonSerializer serializer = new ToDoListJsonSerializer(toDoList);

		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.write(serializer.serialize());
		writer.close();
	}

	private void addToDo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String json = String.format("{\"text\":\"%s\"}", request.getParameter("toDoText"));
		toDoList.addToDo(new ToDoJsonSerializer(json).deserialize());

		getToDoListAndWriteToResponse(1, response);
	}
}
