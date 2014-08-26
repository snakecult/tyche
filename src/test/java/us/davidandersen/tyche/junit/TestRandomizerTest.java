package us.davidandersen.tyche.junit;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import us.davidandersen.tyche.random.ObjectRandomizer;

@RunWith(MockitoJUnitRunner.class)
public class TestRandomizerTest
{
	private TestRandomizer objectUnderTest;

	@Mock
	ObjectRandomizer objectRandomizer;

	@Mock
	private Statement base;

	@Before
	public void setUp()
	{
		objectUnderTest = new TestRandomizer(this);
		assertThat(objectUnderTest.objectRandomizer, instanceOf(ObjectRandomizer.class));
		objectUnderTest.objectRandomizer = objectRandomizer;
	}

	@Test
	public void test() throws Throwable
	{
		objectUnderTest.apply(base, null).evaluate();

		verify(objectRandomizer).randomize(this);
		verify(base).evaluate();
	}
}
