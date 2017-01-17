package com.dokia.webcrawler.tutorial;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

public class TutorialCrawler extends BreadthCrawler{
	
	public TutorialCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		
		addSeed("http://bj.5i5j.com/exchange");
		addRegex("http://bj.5i5j.com/community/([0-9]+)");
		//setExecuteInterval(1000);
		setThreads(5);
		
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		if (page.matchUrl("http://bj.5i5j.com/community/([0-9]+)")) {
			String location = page.select("div[class=house-main]",0)
					.select("h1[class=house-tit]").text();
			//LOG.info(location);
			String[] localInfo = parseLocations(location);
			if (localInfo == null) {
				LOG.error("Fail to parse location info: " + location);
				return;
			}
			
			Elements transactions = page.select("section[class=\"detail-intro fl menu6\"]")
					.select("li[class=watch-record-text]");
			for(Element e : transactions) {
				String[] strArray = e.text().split(" ");
				String[] newArray = new String[localInfo.length + strArray.length];
				for(int i = 0; i < newArray.length; i++) {
					if (i < localInfo.length) {
						newArray[i] = localInfo[i].trim();
					} else {
						newArray[i] = strArray[i - localInfo.length].trim();
					}
				}
				Transaction t = new Transaction();
				try {
					t.loadInfo(newArray);
					LOG.info(t.toString());
				} catch (Exception ex) {
					LOG.error("Fail to load data to transaction object " + e);
				}
				
			}
		} 
	}
	
	private static String[] parseLocations(String location) {
		// location format example: 康盛园  【所在商圈：大兴-黄村】
		// return: 大兴 黄村 康盛园
		if (location == null) return null;

		String[] rawValue = location.trim().split(" |【|：|-|】");
		if (rawValue.length < 4) return null;
		
		String[] result = new String[3];
		result[0] = rawValue[2].trim();
		result[1] = rawValue[3].trim();
		result[2] = rawValue[0].trim();
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		TutorialCrawler crawler = new TutorialCrawler("crawl", true);
		crawler.start(2);
	}
}
