package it.xpug.helloworld;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToDoJsonSerializerTest {

	@Test
	public void testJsonDeserialization() {
		assertEquals(new ToDo("Qualcosa"), new ToDoJsonSerializer("{\"text\":\"Qualcosa\"}").deserialize());
	}
}
