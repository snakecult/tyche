package us.davidandersen.tyche.random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.davidandersen.tyche.Randomize;

@RunWith(MockitoJUnitRunner.class)
public class ObjectRandomizerTest
{
	@Mock
	Randomizer randomizer;

	private ObjectRandomizer objectUnderTest;

	private SomeClass m;

	@Before
	public void setUp()
	{
		objectUnderTest = new ObjectRandomizer(randomizer);
		m = new SomeClass();
	}

	@Test
	public void constructorShouldInitRandomizer()
	{
		final ObjectRandomizer objectRandomizer = new ObjectRandomizer();
		assertThat(objectRandomizer.randomizer, instanceOf(BasicRandomizer.class));
	}

	@Test
	public void randomizeEmptyString()
	{
		when(randomizer.randomize("")).thenReturn("1000");

		objectUnderTest.randomize(m);

		assertEquals("1000", m.x);
	}

	@Test
	public void randomizeString()
	{
		when(randomizer.randomize("bob")).thenReturn("bob9999");

		objectUnderTest.randomize(m);

		assertEquals("bob9999", m.y);
	}

	@Test
	public void randomizeInt()
	{
		when(randomizer.between(0, 9999)).thenReturn(0);

		objectUnderTest.randomize(m);

		assertEquals(0, m.a);
	}

	@Test
	public void randomizeInt2()
	{
		when(randomizer.between(0, 9999)).thenReturn(9999);

		objectUnderTest.randomize(m);

		assertEquals(9999, m.a);
	}

	@Test
	public void randomizeMin()
	{
		when(randomizer.between(-5, 0)).thenReturn(-1);

		objectUnderTest.randomize(m);

		assertEquals(-1, m.min);
	}

	@Test
	public void randomizeMax()
	{
		when(randomizer.between(0, 5)).thenReturn(-4);

		objectUnderTest.randomize(m);

		assertEquals(-4, m.max);
	}

	@Test
	public void randomizeMinMax()
	{
		when(randomizer.between(10, 15)).thenReturn(13);

		objectUnderTest.randomize(m);

		assertEquals(13, m.minmax);
	}

	@Test
	public void shouldUseMinMaxOnString()
	{
		when(randomizer.randomize("bob", 10, 20)).thenReturn("bob17");

		objectUnderTest.randomize(m);

		assertThat(m.minmaxstring, equalTo("bob17"));
	}

	class SomeClass
	{
		@Randomize
		private String x;

		@Randomize("bob")
		private String y;

		@Randomize(value = "bob", min = 10, max = 20)
		private String minmaxstring;

		@Randomize
		private int a;

		@Randomize(min = -5)
		int min;

		@Randomize(max = 5)
		int max;

		@Randomize(min = 10, max = 15)
		int minmax;
	}
}
