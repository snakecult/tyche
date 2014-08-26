package us.davidandersen.tyche.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import us.davidandersen.tyche.random.ObjectRandomizer;

public class TestRandomizer implements TestRule
{
	ObjectRandomizer objectRandomizer;

	private final Object target;

	public TestRandomizer(final Object target)
	{
		objectRandomizer = new ObjectRandomizer();
		this.target = target;
	}

	@Override
	public Statement apply(final Statement base, final Description description)
	{
		return new Statement() {
			@Override
			public void evaluate() throws Throwable
			{
				objectRandomizer.randomize(target);
				base.evaluate();
			}
		};
	}
}
