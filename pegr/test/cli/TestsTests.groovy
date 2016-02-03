import grails.test.AbstractCliTestCase

class TestsTests extends AbstractCliTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTests() {

        execute(["tests"])

        assertEquals 0, waitForProcess()
        verifyHeader()
    }
}
