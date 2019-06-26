package junit;

import org.apache.log4j.Logger;
import org.junit.Assert;

public class AssertWrapper {
    private static final Logger logger = Logger.getLogger(AssertWrapper.class);

    public static void assertTrue(String message, boolean cond) {
        try {
            logMessage(message, cond);
            Assert.assertTrue(cond);
        }catch (AssertionError ae) {
            logger.error("Assertion failed");
        }
    }

    public static void assertEquals(String message, Object expected, Object actual){
        logger.info("ASSERT: Expected: " + expected + ", Current: " + actual);
        Assert.assertEquals(message, expected, actual);
    }

    public static void assertTrueThrowIfFailed(String message, boolean cond){
        logMessage(message, cond);
        try {
            assert cond;
        }catch (AssertionError ae) {
            logger.error("Assertion failed. AssertionError was thrown. ");
            throw new AssertionError(message);
        }

    }

    private static void logMessage(String message, boolean cond){
        logger.info("ASSERT - \"" + message + "\" is  " + cond);
    }
}
