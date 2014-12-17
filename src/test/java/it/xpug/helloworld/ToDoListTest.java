package it.xpug.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ToDoListTest {

	private ToDoList toDoList;

	@Test
	public void getEmptyList() throws Exception {
		toDoList = new ToDoList("Titolo della lista");

		assertEquals("Titolo della lista", toDoList.getTitle());
		assertTrue(toDoList.getToDoList().isEmpty());
	}

	@Test
	public void getToDoListByTitle() throws Exception {
		toDoList = ToDoList.newDefaultToDoList();

		List<Todo> expected = new ArrayList<Todo>();

		expected.add(new Todo("Fai questo"));
		expected.add(new Todo("Quello"));
		expected.add(new Todo("e quell'altro"));

		assertEquals("Titolo della lista", toDoList.getTitle());
		assertEquals(expected, toDoList.getToDoList());
	}
}
