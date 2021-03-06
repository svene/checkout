package org.svenehrke.checkout.derby;

import java.sql.*;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());
	private static final String[] SAMPLE_NAME_DATA = { "John", "Jill", "Jack", "Jerry" };

	public static void main(String[] args) throws Exception {
		new Main().run(args);
	}

	private void run(final String[] args) throws Exception {
		try (Connection con = getConnection()) {
			createSchema(con);
			populateDatabase(con);

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name from employee");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}

	}

	private Connection getConnection() throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		String dbURL = "jdbc:derby:memory:myDB;create=true";
		return DriverManager.getConnection(dbURL);
	}

	private void createSchema(Connection con) throws SQLException {
		Statement st = con.createStatement();
		String table = "create table employee(id integer, name varchar(64))";
		st.executeUpdate(table);
	}

	private void populateDatabase(Connection con) throws SQLException {
		Statement st = con.createStatement();
		for (String name: SAMPLE_NAME_DATA) {
			st.executeUpdate("insert into employee values(1,'" + name + "')");
		}
		logger.info("Populated database");
	}

}
