package com.crawler;

/**
 * @author Pulkit
 * @category Message Constants This class is responsible for declaration of
 *           message constants.
 */
public class CrawlerMessageConstants {

	public static final String CRAWLING = "Crawling: ";
	public static final String FOUND = "Found link: ";
	public static final String ERROR_MESSAGE = "Error inside CrawlImpl.crawl method- Exception occurred during crawling, "
			+ "Either connection is not successful or some other error during processing the urls";
	public static final String ERROR_MESSAGE_SAME_DOMAIN = "Error inside CrawlImpl.isOfSameDomain method ";
	public static final String ERROR_WRITING_FILE = "Error in writing content to file";
	public static final String WRITING_CRAWLED_LINKS = "Crawled links saved to crawled_links.xml, Please check workspace, location is web-crawler-autobot/crawled_links.xml";
}