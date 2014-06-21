Tyche
=====

Tyche is an easy way to create random data for unit tests.

Usage
=====

To use Tyche annotate fields in of a test case with the @Randomize annotation.

This will generate a string of the form "user####", where #### is a number between 1000 and 9999.

    @Randomize("user") // output: user####
    String username;

If you don't like the default value of 1000 - 9999 you may specify min and max.

    @Randomize("user", min=1, max=9) // output: user#
    String username;

If you want a random number leave out the prefix string.

    @Randomize(min=1, max=100) // output: int between 1 and 100
    int number;

And finally, call randomize() on the object you want to randomize.

    new Randomizer().randomize(object); // populate fields annotated with @Randomize

Example
=======

Here's a complete example.

    public class ExampleTest
    {
        @Randomize("user")
        private String username; // will contain "user####"

        @Randomize(min = 1000, max = 9999)
        private int id; // will contain an number between 1000 and 9999

        @Before
        public void setUp()
        {
            new Randomizer().randomize(this); // populate fields annotated with @Randomize
        }
    }
