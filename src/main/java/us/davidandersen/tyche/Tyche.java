package us.davidandersen.tyche;

import us.davidandersen.tyche.random.ObjectRandomizer;

public class Tyche
{
	static ObjectRandomizer objectRandomizer = new ObjectRandomizer();

	public static void randomize(final Object object)
	{
		objectRandomizer.randomize(object);
	}
}
