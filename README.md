Tyche
=====

Tyche is a utility to produce random data for JUnit tests.

Usage
=====

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
