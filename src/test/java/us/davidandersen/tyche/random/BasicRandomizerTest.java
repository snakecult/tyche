package us.davidandersen.tyche.random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.davidandersen.tyche.Randomize;
import us.davidandersen.tyche.Tyche;

@RunWith(MockitoJUnitRunner.class)
public class BasicRandomizerTest
{
	private BasicRandomizer objectUnderTest;

	@Mock
	private BasicRandomizer randomizer;

	@Mock
	private Random random;

	@Randomize(min = 1, max = 50)
	private int min;

	@Randomize(min = 51, max = 100)
	private int max;

	@Before
	public void before()
	{
		Tyche.randomize(this);

		objectUnderTest = new BasicRandomizer(random);
		assertThat(objectUnderTest.randomizer, equalTo(objectUnderTest));
		assertThat(objectUnderTest.random, equalTo(random));

		objectUnderTest.randomizer = randomizer;
	}

	@Test
	public void constructorShouldInitCollaborators()
	{
		final BasicRandomizer r = new BasicRandomizer();
		assertThat(r.randomizer, equalTo(r));
		assertThat(r.random, instanceOf(Random.class));
	}

	@Test
	public void shouldRandomizeWithoutPrefix()
	{
		when(randomizer.between(1000, 9999)).thenReturn(1000);

		assertEquals("1000", objectUnderTest.randomize(""));
	}

	@Test
	public void randomize9999()
	{
		when(randomizer.between(1000, 9999)).thenReturn(9999);

		assertEquals("test9999", objectUnderTest.randomize("test"));
	}

	@Test
	public void shouldReturnANumberBetweenMinAndMax()
	{
		final int v = new Random().nextInt(max - min + 1);
		when(random.nextInt(max - min + 1)).thenReturn(v);

		final int result = objectUnderTest.between(min, max);

		assertThat(result, equalTo(min + v));
	}
}
