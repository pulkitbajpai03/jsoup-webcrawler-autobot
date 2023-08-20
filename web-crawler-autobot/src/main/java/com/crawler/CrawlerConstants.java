package com.crawler;

/**
 * @author Pulkit
 * @category Constants
 * This class is responsible for declaring constants used in implementations.
 * 
 */
public class CrawlerConstants {

	public static final String DOMAIN_URL_TO_CRAWL= "https://dell.com";
    public static final int CRAWLING_DEPTH = 3;
    public static final String BASE_DOMAIN = "https://www.dell.com/*";
    public static final String LINK = "a[href]";
    public static final String DOC_LINK = "abs:href";
    public static final String ELEMENT_LINK = "link";
    public static final String OUTPUT_FILE_NAME =  "crawled_links.xml";
}

