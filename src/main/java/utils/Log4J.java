package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4J {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Log4J.class);
        logger.trace("Step0 ->trace");
        logger.info("Step1 ->info");
        logger.error("Step2 -> error");
        logger.warn("Step3 ->warn");

    }

}
