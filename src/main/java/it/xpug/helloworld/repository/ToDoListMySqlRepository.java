package it.xpug.helloworld.repository;

import it.xpug.helloworld.ToDoList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ToDoListMySqlRepository implements ToDoListRepository {

	private final Databases database;
	private Connection connection;

	public ToDoListMySqlRepository() {
		this.database = Databases.DEVELOPMENT;
	}

	public ToDoListMySqlRepository(Databases database) {
		this.database = database;
	}

	@Override
	public void save(ToDoList toDoList) throws SQLException {

		connect();

		disconnect();
	}

	@Override
	public ToDoList findById(long id) {
		return null;
	}

	public void connect() throws SQLException {
		connection = DriverManager.getConnection(database.getConnectionString());
	}

	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public boolean isConnected() throws SQLException {
		return connection != null && ! connection.isClosed();
	}

	public Object selectOneValue(Object... objects) throws SQLException {
		connect();

		Iterator<Entry<String, Object>> iterator = select(objects).get(0).entrySet().iterator();
		Object resultValue = (iterator.hasNext() ? iterator.next().getValue() : null);

		disconnect();

		return resultValue;
	}

	public void execute(Object... objects) throws SQLException {
		connect();
		prepareStatement(objects).execute();
		disconnect();
	}

	public List<Map<String, Object>> select(Object... objects) throws SQLException {
		connect();

		ResultSet resultSet = prepareStatement(objects).executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		List<Map<String, Object>> selectedValues = new ArrayList<Map<String, Object>>();

		while (resultSet.next()) {
			Map<String, Object> entriesByColumnName = new HashMap<String, Object>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				entriesByColumnName.put(metaData.getColumnLabel(i), resultSet.getObject(i));
			}
			selectedValues.add(entriesByColumnName);
		}

		disconnect();

		return selectedValues;
	}

	private PreparedStatement prepareStatement(Object... objects) throws SQLException {
		PreparedStatement statement = connection.prepareStatement((String) objects[0]);

		for (int i = 1; i < objects.length; i++) {
			statement.setObject(i, objects[i]);
		}

		return statement;
	}
}
