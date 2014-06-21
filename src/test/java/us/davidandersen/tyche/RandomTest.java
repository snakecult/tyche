package us.davidandersen.tyche;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.davidandersen.tyche.Random;

@RunWith(MockitoJUnitRunner.class)
public class RandomTest
{
	@Mock
	java.util.Random random;
	private Random cut;

	@Before
	public void before()
	{
		cut = new Random(random);
	}

	@Test
	public void between()
	{
		when(random.nextInt(0)).thenReturn(0);
		when(random.nextInt(1)).thenReturn(1);

		assertEquals(0, cut.nextInt(0));
		assertEquals(1, cut.nextInt(1));
	}

}
