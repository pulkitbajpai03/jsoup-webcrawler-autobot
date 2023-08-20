package com.crawler;

import static com.crawler.CrawlerConstants.BASE_DOMAIN;
import static com.crawler.CrawlerConstants.CRAWLING_DEPTH;
import static com.crawler.CrawlerConstants.DOC_LINK;
import static com.crawler.CrawlerConstants.ELEMENT_LINK;
import static com.crawler.CrawlerConstants.LINK;
import static com.crawler.CrawlerConstants.OUTPUT_FILE_NAME;
import static com.crawler.CrawlerMessageConstants.ERROR_MESSAGE;
import static com.crawler.CrawlerMessageConstants.ERROR_MESSAGE_SAME_DOMAIN;
import static com.crawler.CrawlerMessageConstants.ERROR_WRITING_FILE;
import static com.crawler.CrawlerMessageConstants.WRITING_CRAWLED_LINKS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Pulkit
 * @category Logic This class contains main logic to crawl on specific domain.
 *           This crawler runs on only specified domains and filters out other
 *           links such as Youtube, Twitter etc. Then saves output in a xml file.
 */
public class CrawlImpl {

	protected static final Logger logger = LogManager.getLogger(CrawlImpl.class);
	private Set<String> crawledLinks = new HashSet<>();
	private Document xmlDocument;

	public void startCrawling(String startingUrl) {
		xmlDocument = Jsoup.parse("<links></links>");
		xmlDocument.outputSettings().prettyPrint(true);
		logger.info("Inside CrawlImpl.startCrawling method");
		/**Crawl on url**/
		crawl(startingUrl, 1);
		/**Save output in file**/
		saveXmlToFile();
	}

	public void crawl(String url, int depth) {
		logger.info("Inside CrawlImpl.crawl method");
		if (depth > CRAWLING_DEPTH || crawledLinks.contains(url)) {
			return;
		}

		crawledLinks.add(url);
		/**
		 * We could use try with resources approach but jsoup class doesn't implement
		 * closable interface hence we are using normal try catch
		 **/
		try {
			/** Using Jsoup to get connection of URL. Jsoup is HTML parser **/
			Document doc = Jsoup.connect(url).get();
			/** This logic extracts and print links from the current page **/
			Elements links = doc.select(LINK);
			/**
			 * Using Stream api feature of java 8 to process links extracted. Using map() to
			 * do mapping of links and filter() to filter out external links
			 **/
			links.stream().map(link -> link.attr(DOC_LINK)).filter(CrawlImpl::isOfSameDomain).forEach(linkUrl -> {
				/**Adding list of crawled urls to xml template**/
				xmlDocument.select("links").get(0).appendChild(createLinkElement(linkUrl));
				/** Using recursion method for depth crawling **/
				crawl(linkUrl, depth + 1);
			});
		} catch (IOException e) {
			logger.error(ERROR_MESSAGE + url, e);
		}
	}

	/**
	 * This method is responsible for filtering out external urls like twitter,
	 * youtube etc.
	 **/
	private static boolean isOfSameDomain(String link) {
		try {
			logger.info("Inside CrawlImpl.isOfSameDomain method");
			URI baseUri = new URI(BASE_DOMAIN);
			URI linkUri = new URI(link);
			return baseUri.getHost().equals(linkUri.getHost());
		} catch (URISyntaxException e) {
			logger.error(ERROR_MESSAGE_SAME_DOMAIN, e);
			return false;
		}
	}

	/**This method is repsponsible to add crawled links in xml template**/
	private Element createLinkElement(String link) {
		logger.info("Inside CrawlImpl.createLinkElement method");
		Element linkElement = new Element(ELEMENT_LINK);
		linkElement.text(link);
		return linkElement;
	}

	/**This method is responsible for generating xml file**/
	private void saveXmlToFile() {
		try {
			logger.info("Inside CrawlImpl.saveXmlToFile method");
			FileWriter fileWriter = new FileWriter(new File(OUTPUT_FILE_NAME));
			fileWriter.write(xmlDocument.outerHtml());
			fileWriter.close();
			logger.info(WRITING_CRAWLED_LINKS);
		} catch (IOException e) {
			logger.error(ERROR_WRITING_FILE, e);
		}
	}

}
