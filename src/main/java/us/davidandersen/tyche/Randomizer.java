package us.davidandersen.tyche;

import java.lang.reflect.Field;

public class Randomizer
{
	private final Random random;

	private final Randomizer randomizer;

	public Randomizer()
	{
		random = new Random();
		randomizer = this;
	}

	protected Randomizer(final Random random)
	{
		this.random = random;
		randomizer = this;
	}

	protected Randomizer(final Random random, final Randomizer randomizer)
	{
		this.random = random;
		this.randomizer = randomizer;
	}

	public String randomize(final String string)
	{
		final String name = string + random.between(1000, 9999);

		return name;
	}

	public void randomize(final Object object)
	{
		for (final Field field : object.getClass().getDeclaredFields())
		{
			if (field.isAnnotationPresent(Randomize.class))
			{
				try
				{
					Randomize rnd = field.getAnnotation(Randomize.class);
					final String value = rnd.value();
					int min = rnd.min();
					int max = rnd.max();

					field.setAccessible(true);
					if ("".equals(value) && (min != 0 || max != 0))
					{
						field.set(object, random.between(min, max));
					}
					else if (field.getType().isAssignableFrom(String.class))
					{
						field.set(object, randomizer.randomize(value));
					}
					else
					{
						field.set(object, random.between(0, 9999));
					}
				}
				catch (final Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}
}
