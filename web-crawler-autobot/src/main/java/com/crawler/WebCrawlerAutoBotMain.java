package com.crawler;

import static com.crawler.CrawlerConstants.CRAWLING_DEPTH;
import static com.crawler.CrawlerConstants.DOMAIN_URL_TO_CRAWL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Pulkit
 * @category Main method
 * This is main method of the application, Please select this method do right
 * click and select run as Java application
 **/

public class WebCrawlerAutoBotMain {

	protected static final Logger logger = LogManager.getLogger(WebCrawlerAutoBotMain.class);

	public static void main(String[] args) {
		logger.info("In WebCrawlerAutoBotMain.main method--Domain to clawl is " + DOMAIN_URL_TO_CRAWL
				+ " Crawling depth is " + CRAWLING_DEPTH);
		CrawlImpl crawl = new CrawlImpl();
		crawl.startCrawling(DOMAIN_URL_TO_CRAWL);
	}

}
