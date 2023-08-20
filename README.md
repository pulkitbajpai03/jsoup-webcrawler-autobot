# Domain-Specific Web Crawler Assignment

This Java 8 program demonstrates a simple web crawler that crawls a dell domain, crawling and printing links from web pages. It utilizes the Jsoup library for web page parsing and maven for dependency management.

## Features

- Crawls web pages within a dell domain while skipping external links like Google, twitter, youtube etc.
- Extracts and prints links from web pages to a xml file.
- Supports depth-based crawling (up to a specified depth level).

## Prerequisites

- Java 8 or higher
- Jsoup library (provided by maven in the project)
- Maven


## Usage through Eclipse IDE(Please prefer this method)

1. Clone this repository using or copy the code to your local environment.

	git clone https://github.com/pulkitbajpai03/jsoup-webcrawler-autobot.git
	
2. Import cloned repository as maven project.
    a. Right click in project explorer.
	b. Maven->Import as maven porject.
	c. After import, Select project and do a maven update.(completes in couple of seconds)
	
3. Right click on project web-crawler-autobot and go to maven run configuration
	a. Select project in workspace
	b. Enter goals: clean install
	c. Apply and run
	
4. Go to main file, right click and select run as java project. Results will be printed in xml file which can be find inside web-crawler-autobot/.


## Usage through command line

1. Clone this repository using or copy the code to your local environment.

	git clone https://github.com/pulkitbajpai03/jsoup-webcrawler-autobot.git

2. Open the `web-crawler-autobot` project.
	
	cd web-crawler-autobot

3. Run the program:
   
    mvn clean install
   java -cp /web-crawler-autobot/target/web-crawler-autobot-0.0.1-SNAPSHOT.jar com.crawler.WebCrawlerAutoBotMain
