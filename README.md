## J-Unit5 (Jupiter Testing)
- Unit Testing is done by developer itself rather than involving Tester/QAs.
- Testing in SDLC is different from Unit Testing.

### Steps for Unit Testing
- Prepare (Setup a Test Env, write Test Methods)
- Provide Testing Input
- Run the Test
- Provide Expected Output
- Perform Assertion (Verifying the Actual with Expected result)
- Report Test Results (Alert Developer if test is failed or passed)

### @Test Annotation
- Applied over methods to mark method as Test (org.junit.jupiter.api —> JUnit 5)
- Test method should only be public in J-Unit 3, 4.
- Visibility of @Test annotated method can be public, protected , default.
- Also informs test engine what method needs to run.

### Assertions.
- Comparing the result of Expected and Actual.
- Being a developer, Actual and expected result is to be defined in the test cases.
- if Both Expected and Actual result matches, its considered as Success.
- if Both Expected and Actual result doesn’t matches, its considered as Failure.
- Assertion methods are used with import static org.junit.jupiter.Assertions.* class
- Ex. APIs are assertEquals(expected, actual)

### AssertArrayEquals()
- Actual and expected arrays are equal and AssertEquals will not work as it is comparing the reference objects and not the content.
    - Number if elements should match
    - Elements of an array are equal
    - Order of elements in an array

### AssertThrows()
- Actual and expected should throw an exception and verifying the same.

### AssertTimeout()
- Actual and expected should execute within the timeout in-order to validate performance. Duration.ofMillis(10) used to validate the execution is within 10 milliseconds.

### @BeforeEach
- Static block which can be used to execute before each test methods to instantiate the test case. It works with constructor of the Test Class. ex: Object creation for the classes used in the test method.

### @AfterEach
- Static block which can be used to execute after every test methods to close the resources used in the test case.  It works with constructor of the Test Class. ex: Object destructor for the classes used in the test method.

### @BeforeAll
- Static block which can be used to execute before all test methods to instantiate the test case. ex: Object creation for the classes used in the test method. Should be used only for static methods and it will be executed only once.

### @AfterAll
- Static block which can be used to executing all test methods to close the resources used in the test case. ex: Object destructor for the classes used in the test method. Should be used only for static methods and it will be executed only once.

### @TestInstance
- Its used to define the lifecycle of the Test Instance or Object Creation for the Test Class
- its provided to the Class level, with the below statement
- @TestInstance(TestInstance.Lifecycle.PER_CLASS) 0r @TestInstance(TestInstance.Lifecycle.PER_METHOD)
- Behaviour :
    - PER_CLASS
        - BeforeAll will be executed only once and static method is not required.
        - AfterAll will be executed only once and static method is not required.
    - PER_METHOD
        - BeforeAll will be executed only once and static method is required.
        - AfterAll will be executed only once and static method is not required.

### Mockito
- Mockito is a popular framework used for mock the objects and software tests
- Using Mockito greatly simplies the development of tests for classes with external dependency
- Mock Object is a dummy implementation for an interface or a class
- It allows to define the output of certain method they typically record the interaction with the system and the tests can validate that.
- @Mock - is used to the class which needs an dummy objects created.
- @InjectMocks - is used to the class which accpets the dummy mocked objects.
- Mockito.when(<Mocked Object>).thenReturn(<Mocked Result>)
- MockHttpServletRequestBuilder & MockMvcRequestBuilder is used to mock the API service calls from Test Case
- MockMvc is used to perform the mock request and to validate the expected results.
- ObjectMapper and ObjectWriter is used to create the content for Mock Requests (Converting Java Pojo’s to String)

### TDD (Test Driven Development)
- Write the Test Case first and then the actual code
- Based on the Test Result, the code can be updated accordingly.