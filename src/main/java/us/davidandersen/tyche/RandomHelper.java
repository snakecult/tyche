package us.davidandersen.tyche;

import java.util.Random;

public class RandomHelper
{
	private final Random random;

	public RandomHelper()
	{
		this(new Random());
	}

	public RandomHelper(final Random random)
	{
		this.random = random;
	}

	public int between(final int min, final int max)
	{
		final int num = random.nextInt(max - min + 1) + min;

		return num;
	}

	public int nextInt()
	{
		return random.nextInt();
	}

	public int nextInt(final int n)
	{
		return random.nextInt(n);
	}

	public float nextFloat()
	{
		return random.nextFloat();
	}

	public double nextDouble()
	{
		return random.nextDouble();
	}
}
