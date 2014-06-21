package us.davidandersen.tyche;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RandomHelperTest
{
	private RandomHelper objectUnderTest;

	@Mock
	private Random random;

	@Randomize(min = 1, max = 50)
	private int min;

	@Randomize(min = 51, max = 100)
	private int max;

	@Before
	public void before()
	{
		new Randomizer().randomize(this);
		objectUnderTest = new RandomHelper(random);
	}

	@Test
	public void shouldReturnTheNextInt()
	{
		when(random.nextInt(0)).thenReturn(0);
		when(random.nextInt(1)).thenReturn(1);

		assertThat(objectUnderTest.nextInt(0), equalTo(0));
		assertThat(objectUnderTest.nextInt(1), equalTo(1));
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
