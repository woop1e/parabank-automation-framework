package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoggerUtil - Handles logging throughout the framework
 */
public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    /**
     * Log info level message
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log debug level message
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log warning level message
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log error level message
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log fatal level message
     */
    public static void fatal(String message) {
        logger.fatal(message);
    }
}
