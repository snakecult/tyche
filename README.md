# Tyche

Tyche is an easy way to create random data for unit tests.

## How it Works

Tyche looks for fields annotated with @Randomize and fills them with random data.

This will generate a string of the form "user####", where #### is a number between 1000 and 9999.

    @Randomize("user")
    String username; // output: user####

If you don't like the default value of 1000 - 9999 you may specify min and max.

    @Randomize(value = "user", min = 1, max = 9)
    String username; // output: user#

If you want a random number leave out the prefix string.

    @Randomize(min = 1, max = 100)
    int number; // output: int between 1 and 100

And finally, call Tyche.randomize() on the object you want to randomize.

    Tyche.randomize(object); // populate fields annotated with @Randomize

## JUnit Example

In this example test data is randomized by by calling Tyche.randomize().

    @RunWith(MockitoJUnitRunner.class)
    public class ExampleTest
    {
        MyController objectUnderTest;

        @Mock
        AuthenticationService authenticationService;

        @Randomize("username")
        private String username;

        @Randomize("password")
        private String password;

        @Randomize("token")
        private String token;

        @Before
        public void setUp()
        {
            Tyche.randomize(this);
            objectUnderTest = new MyController(service);
        }

        @Test
        public void myTest()
        {
            when(authenticationService.authenticate(username, password)).thenReturn(token);

            Cookie cookie = objectUnderTest.login(username, password);

            assertThat(cookie.getValue, equalTo(token));
        }
    }

## JUnit @Rule Example

The JUnit TestRandomizer rule is also available.

    public class ExampleTest
    {
        @Rule
        public TestRandomizer testRandomizer = new TestRandomizer(this);

        @Randomize(min = 1000, max = 9999)
        private int id;

        @Test
        public void myTest()
        {
            // ...
        }
    }

## Get Tyche

Tyche is available via Maven.

    <dependency>
        <groupId>us.davidandersen.tyche</groupId>
        <artifactId>tyche</artifactId>
        <version>0.2-SNAPSHOT</version>
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
