package org.svenehrke.checkout.h2withlambdas;

import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

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

		showInternals(c);
		showDDL(c);

	}

	private void showInternals(Connection c) {
		// This is jOOQ's way of executing the above
		// statement. Result implements List, which
		// makes subsequent steps much easier
		DSL.using(c)
			.fetch("select table_name, column_name " +
				"from information_schema.columns " +
				"order by " +
				"table_catalog, " +
				"table_schema, " +
				"table_name, " +
				"ordinal_position")
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

	private void showDDL(Connection c) {
		String sql =
			"select " +
				"table_name, " +
				"column_name, " +
				"type_name " + // Add the column type
				"from information_schema.columns " +
				"order by " +
				"table_catalog, " +
				"table_schema, " +
				"table_name, " +
				"ordinal_position";

		DSL.using(c).fetch(sql)
			.stream()
			.collect(groupingBy(
				r -> r.getValue("TABLE_NAME"),
				LinkedHashMap::new,
				mapping(

					// We now collect this new wrapper type
					// instead of just the COLUMN_NAME
					r -> new Column(
						r.getValue("COLUMN_NAME", String.class),
						r.getValue("TYPE_NAME", String.class)
					),
					toList()
				)
			))
			.forEach(
				(table, columns) -> {

					// Just emit a CREATE TABLE statement
					System.out.println(
						"CREATE TABLE " + table + " (");

					// Map each "Column" type into a String
					// containing the column specification,
					// and join them using comma and
					// newline. Done!
					System.out.println(columns.stream().map(col -> "  " + col.name + " " + col.type).collect(Collectors.joining(",\n")));

					System.out.println(");");
				}
			);
	}

	private static class Column {
		final String name;
		final String type;

		Column(String name, String type) {
			this.name = name;
			this.type = type;
		}
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
