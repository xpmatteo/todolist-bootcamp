package it.xpug.helloworld.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ToDoListMySqlRepositoryTest {

	private ToDoListMySqlRepository toDoListMySqlRepository;

	@Before
	public void setup() throws SQLException {
		toDoListMySqlRepository = new ToDoListMySqlRepository(Databases.TEST);

		toDoListMySqlRepository
				.execute("create table if not exists prova (x varchar(255), y int)");

		toDoListMySqlRepository.execute("truncate prova");
	}

	@Test
	public void testMySqlConnection() throws SQLException {

		toDoListMySqlRepository.connect();

		assertTrue(toDoListMySqlRepository.isConnected());

	}

	@Test
	public void selectOnwValue() throws Exception {
		assertEquals(7L,
				toDoListMySqlRepository.selectOneValue("select ? + ?", 3, 4));
		assertEquals("ciao mamma", toDoListMySqlRepository.selectOneValue(
				"select concat(?,?)", "ciao", " mamma"));
	}

	@Test
	public void selectMoreRows() throws Exception {
		toDoListMySqlRepository.execute("insert into prova values (?, ?)",
				"ciao", 42);
		List<?> rows = toDoListMySqlRepository
				.select("select * from prova");
		assertEquals(1L, rows.size());
		assertEquals("ciao",((Map<String,Object>) rows.get(0)).get("x"));
	}

	@Test
	public void executeStatement() throws Exception {

		assertEquals(0L, toDoListMySqlRepository.selectOneValue("select count(*) from prova"));
		toDoListMySqlRepository.execute("insert into prova values (?,?)", "ciao", 1);
		assertEquals(1L, toDoListMySqlRepository.selectOneValue("select count(*) from prova where x = 'ciao'"));
	}
}
