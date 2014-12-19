package it.xpug.helloworld.repository;

import it.xpug.helloworld.ToDoList;

public interface ToDoListRepository {
	void save(ToDoList toDoList);

	ToDoList findById(long id);

}
