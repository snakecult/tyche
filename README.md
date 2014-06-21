Tyche
=====

Tyche is an easy way to create random data for unit tests.

Usage
=====

To use Tyche annotate fields in of a test case with the @Randomize annotation.

This will generate a string of the form "user####", where #### is a number between 1000 and 9999.

    @Randomize("user")
    String username; // output: user####

If you don't like the default value of 1000 - 9999 you may specify min and max.

    @Randomize(value = "user", min = 1, max = 9)
    String username; // output: user#

If you want a random number leave out the prefix string.

    @Randomize(min = 1, max = 100)
    int number; // output: int between 1 and 100

And finally, call `Tyche.randomize()` on the object you want to randomize.

    Tyche.randomize(object); // populate fields annotated with @Randomize

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
            Tyche.randomize(this); // populate fields annotated with @Randomize
        }
    }

Maven
=====

Tyche is available via Maven.

    <dependency>
        <groupId>us.davidandersen.tyche</groupId>
        <artifactId>tyche</artifactId>
        <version>0.1-SNAPSHOT</version>
    </dependency>

    <repositories>
        <repository>
            <id>da-public</id>
            <url>http://artifactory.davidandersen.us/public</url>
        </repository>
        <snapshotRepository>
            <id>da-public-snapshot</id>
            <url>http://artifactory.davidandersen.us/public-snapshot</url>
        </snapshotRepository>
    </repositories>
