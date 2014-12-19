package it.xpug.helloworld.repository;

import static org.junit.Assert.*;

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
		toDoListMySqlRepository.execute("create table if not exists prova (x varchar(255), y int)");
		toDoListMySqlRepository.execute("truncate prova");
	}

	@Test
	public void testMySqlConnection() throws SQLException {
		toDoListMySqlRepository.connect();
		assertTrue(toDoListMySqlRepository.isConnected());
	}

	@Test
	public void selectOneValue() throws Exception {
		assertEquals(7L, toDoListMySqlRepository.selectOneValue("select ? + ?", 3, 4));
		assertEquals("ciao mamma", toDoListMySqlRepository.selectOneValue("select concat(?,?)", "ciao", " mamma"));
	}

	@Test
	public void executeStatement() throws Exception {
		assertEquals(0L, toDoListMySqlRepository.selectOneValue("select count(*) from prova"));
		toDoListMySqlRepository.execute("insert into prova values (?,?)", "ciao", 1);
		assertEquals(1L, toDoListMySqlRepository.selectOneValue("select count(*) from prova where x = 'ciao'"));
	}

	@Test
	public void selectMoreRows() throws Exception {
		toDoListMySqlRepository.execute("insert into prova values (?, ?)", "ciao", 42);
		List<Map<String, Object>> rows = toDoListMySqlRepository.select("select * from prova");
		assertEquals(1L, rows.size());
		assertEquals("ciao",rows.get(0).get("x"));
	}

	@Test
	public void selectMoreRowsWithParams() throws Exception {
		toDoListMySqlRepository.execute("insert into prova values (?, ?)", "more_rows_with_params", 42);
		List<Map<String,Object>> rows = toDoListMySqlRepository.select("select * from prova where x = ? and y = ?", "more_rows_with_params", 42);
		assertEquals(1L, rows.size());
		assertEquals("more_rows_with_params", rows.get(0).get("x"));
	}
}
