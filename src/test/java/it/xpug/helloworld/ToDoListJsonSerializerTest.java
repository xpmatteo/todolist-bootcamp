package it.xpug.helloworld;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ToDoListJsonSerializerTest {

	private ToDoList toDoList;

	@Before
	public void setup() {
		toDoList = ToDoList.newDefaultToDoList();
	}

	@Test
	public void testJsonSerialization() throws Exception {
		ToDoListJsonSerializer jsonSerializer = new ToDoListJsonSerializer(toDoList);

		assertEquals(
				"{\"title\":\"Titolo della lista\",\"toDos\":[\"Fai questo\",\"Quello\",\"e quell\\u0027altro\"]}",
				jsonSerializer.toString());
	}

}
