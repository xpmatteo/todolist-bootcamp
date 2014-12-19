package it.xpug.helloworld.repository;

public enum Databases {

	DEVELOPMENT("todolist_development",
			"jdbc:mysql://localhost/todolist_development?"
					+ "user=todolist&password=todolist"), TEST("todolist_test",
			"jdbc:mysql://localhost/todolist_test?"
					+ "user=todolist&password=todolist");

	final String databaseName;
	final String connectionString;

	private Databases(String databaseName, String connectString) {
		this.databaseName = databaseName;
		this.connectionString = connectString;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getConnectionString() {
		return connectionString;
	}

}
