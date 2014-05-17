package org.svenehrke.checkout.h2withlambdas;

import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class Main {

	public static void main(String[] args) {

		new Main().doit();
	}

	private void doit() {

		Connection c = newConnection();

		// This SQL statement produces all table
		// names and column names in the H2 schema
		String sql =
			"select table_name, column_name " +
				"from information_schema.columns " +
				"order by " +
				"table_catalog, " +
				"table_schema, " +
				"table_name, " +
				"ordinal_position";


		// This is jOOQ's way of executing the above
		// statement. Result implements List, which
		// makes subsequent steps much easier
		DSL.using(c)
			.fetch(sql)
			.stream()
			.collect(groupingBy(
				r -> r.getValue("TABLE_NAME"),
				mapping(
					r -> r.getValue("COLUMN_NAME"),
					toList()
				)
			))
			.forEach(
				(table, columns) ->
					System.out.println(table + ": " + columns)
			);
	}


	private Connection newConnection() {
		try {
			Class.forName("org.h2.Driver");
			String h2DBFilename = "~/checkout_h2";
			return DriverManager.getConnection("jdbc:h2:" + h2DBFilename, "sa", "");
		} catch (Exception e) {
			throw new RuntimeException("cannot create new DB-connection", e);
		}
	}

}
