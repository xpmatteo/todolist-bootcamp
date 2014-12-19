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
import java.util.List;
import java.util.Map;

public class ToDoListMySqlRepository implements ToDoListRepository {

	private final Databases database;
	private Connection connection;

	private PreparedStatement prepareStatement(Object... objects)
			throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement((String) objects[0]);

		for (int i = 1; i < objects.length; i++) {
			statement.setObject(i, objects[i]);
		}

		return statement;
	}

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
		connection = DriverManager
				.getConnection(database.getConnectionString());
	}

	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	public boolean isConnected() throws SQLException {
		return connection != null && !connection.isClosed();

	}

	public Object selectOneValue(Object... objects) throws SQLException {
		connect();

		PreparedStatement statement = prepareStatement(objects);

		ResultSet resultSet = statement.executeQuery();

		resultSet.next();

		Object resultValue = resultSet.getObject(1);

		disconnect();

		return resultValue;

	}

	public void execute(Object... objects) throws SQLException {
		connect();

		PreparedStatement statement = prepareStatement(objects);

		statement.execute();

		disconnect();

	}

	public List<?> select(String string) throws SQLException {
		connect();

		PreparedStatement statement = prepareStatement(string);

		ResultSet resultSet = statement.executeQuery();
		List<Object> selectedValues = new ArrayList<Object>();

		ResultSetMetaData metaData = resultSet.getMetaData();

		while (resultSet.next()) {
			Map<String, Object> entriesByColumnName = new HashMap<String, Object>();

			for (int i = 1; i < metaData.getColumnCount(); i++) {
				entriesByColumnName.put(metaData.getColumnName(i),
						resultSet.getObject(i));
			}

			selectedValues.add(entriesByColumnName);

		}

		disconnect();

		return selectedValues;
	}

}
