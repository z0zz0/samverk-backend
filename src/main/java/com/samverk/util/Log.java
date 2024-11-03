package com.samverk.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    
    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message, Object... params)
    {
        logger.info(message, params);
    }

    public static void debug(String message, Object... params) 
    {
        logger.debug(message, params);
    }

    public static void warn(String message, Object... params) 
    {
        logger.warn(message, params);
    }

    public static void error(String message, Object... params) 
    {
        logger.error(message, params);
    }
}
