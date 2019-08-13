package com.github.mstraub.devbrc.boundaries;
//package com.github.devbrc.boundaries;
//
//import org.apache.log4j.ConsoleAppender;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PatternLayout;
//import org.junit.Test;
//
//public class LearningTest {
//
//	@Test
//	public void testLogCreate_DoesNotPrintAnything() {
//		Logger logger = Logger.getLogger("MyLogger");
//		logger.info("hello");
//	}
//
//	@Test
//	public void testLogAddAppender_StillNothing() {
//		Logger logger = Logger.getLogger("MyLogger");
//		ConsoleAppender appender = new ConsoleAppender();
//		logger.addAppender(appender);
//		logger.info("hello");
//	}
//
//	@Test
//	public void testLogAddAppender_FinallyItWorks() {
//		Logger logger = Logger.getLogger("MyLogger");
//		logger.removeAllAppenders();
//		logger.addAppender(new ConsoleAppender(new PatternLayout("%p %t %m%n"),
//				ConsoleAppender.SYSTEM_OUT));
//		logger.info("hello");
//	}
//
//}
