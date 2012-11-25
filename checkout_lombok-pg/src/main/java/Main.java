public class Main {
	public static void main(String[] args) {
		new Main().doit();
	}

	private void doit() {
		Person p = Person.person().name("Ehrke").firstname("Sven").age(99).build();

		System.out.println(p);
	}
}
