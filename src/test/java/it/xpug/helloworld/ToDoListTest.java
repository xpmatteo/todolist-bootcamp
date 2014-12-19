package it.xpug.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ToDoListTest {

	private ToDoList toDoList;

	@Before
	public void setUp() {
		toDoList = new ToDoList("Titolo della lista");
	}

	@Test
	public void getEmptyListWithTitle() throws Exception {
		assertEquals("Titolo della lista", toDoList.getTitle());
		assertTrue(toDoList.getToDoList().isEmpty());
	}

	@Test
	public void addToDoToEmptyToDoList() throws Exception {
		ToDo toDo = new ToDo("Fai questo");
		toDoList.addToDo(new ToDo(toDo.getText()));
		assertEquals(toDo, toDoList.getToDoList().get(0));
	}
}
