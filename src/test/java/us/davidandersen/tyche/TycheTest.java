package us.davidandersen.tyche;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import org.junit.Test;
import us.davidandersen.tyche.random.ObjectRandomizer;

public class TycheTest
{
	@Test
	public void objectRandomizerShouldBeInitialized()
	{
		assertThat(Tyche.objectRandomizer, instanceOf(ObjectRandomizer.class));
	}

	@Test
	public void objectShouldBeRandomized()
	{
		final MyObject obj = new MyObject();
		Tyche.randomize(obj);

		assertThat(obj.var, equalTo("var1"));
	}

	class MyObject
	{
		@Randomize(value = "var", min = 1, max = 1)
		String var;
	}
}
