package it.xpug.helloworld;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
	private long id;
	private String title;
	private List<ToDo> toDos = new ArrayList<ToDo>();

	public static ToDoList newDefaultToDoList() {
		ToDoList toDoList = new ToDoList("Titolo della lista");

		toDoList.addToDo(new ToDo("Fai questo"));
		toDoList.addToDo(new ToDo("Quello"));
		toDoList.addToDo(new ToDo("e quell'altro"));

		return toDoList;
	}

	public ToDoList(String title) {
		this.title = title;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<ToDo> getToDoList() {
		return toDos;
	}

	public void addToDo(ToDo todo){
		toDos.add(todo);
	}
}
