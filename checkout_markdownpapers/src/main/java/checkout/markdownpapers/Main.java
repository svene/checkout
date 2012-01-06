package checkout.markdownpapers;

import org.tautua.markdownpapers.Markdown;

import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		final InputStream is = Main.class.getResourceAsStream("/in.md");
		if (is == null) {
			System.out.println("cannot read input");
			return;
		}
		Reader in = new InputStreamReader(is);
		Writer out = new FileWriter("out.html");
		Markdown md = new Markdown();

		md.transform(in, out);
		out.flush();
		out.close();
		is.close();
	}
}
