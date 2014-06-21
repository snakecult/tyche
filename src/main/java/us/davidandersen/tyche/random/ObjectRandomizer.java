package us.davidandersen.tyche.random;

import java.lang.reflect.Field;
import us.davidandersen.tyche.Randomize;

public class ObjectRandomizer
{
	final Randomizer randomizer;

	public ObjectRandomizer()
	{
		randomizer = new BasicRandomizer();
	}

	ObjectRandomizer(final Randomizer randomizer)
	{
		this.randomizer = randomizer;
	}

	public void randomize(final Object object)
	{
		for (final Field field : object.getClass().getDeclaredFields())
		{
			if (field.isAnnotationPresent(Randomize.class))
			{
				try
				{
					final Randomize randomizeAnnotation = field.getAnnotation(Randomize.class);
					final String value = randomizeAnnotation.value();
					final int min = randomizeAnnotation.min();
					final int max = randomizeAnnotation.max();

					field.setAccessible(true);
					if ("".equals(value) && (min != 0 || max != 0))
					{
						field.set(object, randomizer.between(min, max));
					}
					else if (field.getType().isAssignableFrom(String.class) && (min != 0 || max != 0))
					{
						field.set(object, randomizer.randomize(value, min, max));
					}
					else if (field.getType().isAssignableFrom(String.class))
					{
						field.set(object, randomizer.randomize(value));
					}
					else
					{
						field.set(object, randomizer.between(0, 9999));
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
