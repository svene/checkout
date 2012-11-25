import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Person {
	private final String name;
	private final String firstname;
	private int age;

}
