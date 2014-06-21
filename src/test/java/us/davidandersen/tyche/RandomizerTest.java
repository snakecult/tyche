package us.davidandersen.tyche;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.davidandersen.tyche.RandomHelper;
import us.davidandersen.tyche.Randomize;
import us.davidandersen.tyche.Randomizer;

@RunWith(MockitoJUnitRunner.class)
public class RandomizerTest
{
	@Mock
	RandomHelper random;

	@Mock
	Randomizer randomizer;

	private Randomizer objectUnderTest;

	private SomeClass m;

	@Before
	public void before()
	{
		objectUnderTest = new Randomizer(random, randomizer);

		m = new SomeClass();
	}

	@Test
	public void randomize1000()
	{
		when(random.between(1000, 9999)).thenReturn(1000);

		assertEquals("1000", objectUnderTest.randomize(""));
	}

	@Test
	public void randomize9999()
	{
		when(random.between(1000, 9999)).thenReturn(9999);

		assertEquals("test9999", objectUnderTest.randomize("test"));
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
		when(random.between(0, 9999)).thenReturn(0);

		objectUnderTest.randomize(m);

		assertEquals(0, m.a);
	}

	@Test
	public void randomizeInt2()
	{
		when(random.between(0, 9999)).thenReturn(9999);

		objectUnderTest.randomize(m);

		assertEquals(9999, m.a);
	}

	@Test
	public void randomizeMin()
	{
		when(random.between(-5, 0)).thenReturn(-1);

		objectUnderTest.randomize(m);

		assertEquals(-1, m.min);
	}

	@Test
	public void randomizeMax()
	{
		when(random.between(0, 5)).thenReturn(-4);

		objectUnderTest.randomize(m);

		assertEquals(-4, m.max);
	}

	@Test
	public void randomizeMinMax()
	{
		when(random.between(10, 15)).thenReturn(13);

		objectUnderTest.randomize(m);

		assertEquals(13, m.minmax);
	}

	class SomeClass
	{
		@Randomize
		private String x;

		@Randomize("bob")
		private String y;

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
