package checkout.spark;

import static spark.Spark.*;
import spark.*;

/**
 * See http://www.sparkjava.com
 * Run this Main and point browser to : http://localhost:4567/hello
 */
public class Main {
	public static void main(String[] args) {
		get(new Route("/hello") {
			@Override
			public Object handle(Request aRequest, Response aResponse) {
				return "Hello World!";
			}
		});
	}
}
