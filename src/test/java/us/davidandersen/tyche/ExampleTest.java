package us.davidandersen.tyche;

import org.junit.Before;

public class ExampleTest
{
	@Randomize("user")
	private String username;

	@Randomize(min = 1000, max = 9999)
	private int id;

	@Before
	public void setUp()
	{
		Tyche.randomize(this);
	}
}
