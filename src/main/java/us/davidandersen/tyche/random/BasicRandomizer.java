package us.davidandersen.tyche.random;

import java.util.Random;

/**
 * Basic implementation of {@link Randomizer}.
 *
 * @author David Andersen
 *
 */
public class BasicRandomizer implements Randomizer
{
	Random random;

	BasicRandomizer randomizer;

	public BasicRandomizer()
	{
		random = new Random();
		randomizer = this;
	}

	public BasicRandomizer(final Random random)
	{
		this.random = random;
		randomizer = this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String randomize(final String prefix)
	{
		final String value = randomize(prefix, 1000, 9999);

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String randomize(final String prefix, final int min, final int max)
	{
		final String value = prefix + randomizer.between(min, max);

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int between(final int min, final int max)
	{
		final int num = random.nextInt(max - min + 1) + min;

		return num;
	}
}
