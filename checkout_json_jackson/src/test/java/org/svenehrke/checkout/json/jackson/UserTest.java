package org.svenehrke.checkout.json.jackson;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class UserTest {

	public static final String JSON_STRING = "{`name`:{`first`:`Uli`,`last`:`Ehrke`},`gender`:`FEMALE`,`userImage`:`YmxhYmxh`,`verified`:true}";

	@Test
	public void testObjectToJSONString() throws Exception {
		final User user = newTestUser();

		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, user);

		String expected = toQuotes(JSON_STRING);
		assertEquals(expected, sw.toString());
	}

	private User newTestUser() {
		final User user = new User();
		user.setGender(User.Gender.FEMALE);
		final User.Name name = new User.Name();
		name.setFirst("Uli");
		name.setLast("Ehrke");
		user.setName(name);
		user.setUserImage("blabla".getBytes());
		user.setVerified(true);
		return user;
	}

	@Test
	public void testJSONStringToObject() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		final User user = mapper.readValue(new StringReader(toQuotes(JSON_STRING)), User.class);
		assertEquals(newTestUser(), user);
	}

	private String toQuotes(final String s) {
		return s.replaceAll("`", "\"");
	}
	private String toBackTicks(final String s) {
		return s.replaceAll("\"", "`");
	}
}
