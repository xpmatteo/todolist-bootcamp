package it.xpug.helloworld;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {

	private String title;
	private List<String> toDos = new ArrayList<String>();

	public static ToDoList newDefaultToDoList() {
		ToDoList toDoList = new ToDoList("Titolo della lista");

		toDoList.addToDo("Fai questo");
		toDoList.addToDo("Quello");
		toDoList.addToDo("e quell'altro");

		return toDoList;
	}

	public ToDoList(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getToDoList() {
		return toDos;
	}

	public void addToDo(String toDo) {
		toDos.add(toDo);
	}
}