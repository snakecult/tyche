package us.davidandersen.tyche.random;

/**
 * Interface to generate random values.
 *
 * @author David Andersen
 */
public interface Randomizer
{
	/**
	 * Return a string of format "prefix + (number between 1000 and 9999)".
	 */
	String randomize(String prefix);

	/**
	 * Return a string of format "prefix + (number between min and max)".
	 */
	String randomize(String prefix, int min, int max);

	/**
	 * Return a number between min and max.
	 */
	int between(int min, int max);
}
