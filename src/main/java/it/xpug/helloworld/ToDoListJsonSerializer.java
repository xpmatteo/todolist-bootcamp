package it.xpug.helloworld;

import com.google.gson.Gson;

public class ToDoListJsonSerializer {

	private ToDoList toDoList;

	public ToDoListJsonSerializer(ToDoList toDoList) {
		this.toDoList = toDoList;
	}

	public String toString() {
		return new Gson().toJson(toDoList);
	}
}
