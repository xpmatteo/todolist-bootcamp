package it.xpug.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ToDoListTest {

	private ToDoList toDoList;

	@Before
	public void setup() {
		toDoList = new ToDoList("Titolo della lista");
	}

	@Test
	public void getEmptyList() throws Exception {
		assertEquals("Titolo della lista", toDoList.getTitle());
		assertTrue(toDoList.getToDoList().isEmpty());
	}

	@Test
	public void getToDoListByTitle() throws Exception {
		List<String> expected = Arrays.asList("Fai questo", "Quello", "e quell'altro");
		for (String string : expected) {
			toDoList.addToDo(string);
		}

		assertEquals("Titolo della lista", toDoList.getTitle());
		assertEquals(expected, toDoList.getToDoList());
	}
}
