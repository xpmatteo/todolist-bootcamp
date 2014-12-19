package it.xpug.helloworld.repository;

import java.sql.SQLException;

import it.xpug.helloworld.ToDoList;

public interface ToDoListRepository {
	void save(ToDoList toDoList) throws SQLException;

	ToDoList findById(long id);

}
