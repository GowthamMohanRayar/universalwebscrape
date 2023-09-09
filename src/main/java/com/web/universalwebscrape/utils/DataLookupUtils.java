package com.web.universalwebscrape.utils;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import io.micrometer.common.util.StringUtils;

public class DataLookupUtils {
	
	public static String extractBeta(Elements parentElement) {
		for (Element tableElement : parentElement) {
			for (Element rowElement : tableElement.select("td").select("td")) {
				if (null != rowElement.select("div.nsebeta")
						&& StringUtils.isNotBlank(rowElement.select("div.nsebeta").text())) {
					return rowElement.select("div.nsebeta").text();
				}
			}
		}
		return "";
	}

	public static Object extractInsiderData(Elements parentElement) {
		Map<String, Map<String, String>> mainDataMap = new HashMap<>();
		Map<String, String> subDataMap = null;
		int i = 0;
		for (Element mainElement : parentElement) {
			subDataMap = new HashMap<>();
			Elements subElement = mainElement.select("div.clearfix");
			subDataMap.put("date", subElement.select("div.br_date").text());
			subDataMap.put("buyOrSell", subElement.select("button").text());
			subDataMap.put("name", mainElement.select("div.brstk_name").select("h3").text());
			subDataMap.put("desinper", mainElement.select("div.br_date").text());

			Elements tableElements = mainElement.select("table").select("tbody").select("td");
			for (Element tableElement : tableElements) {
				subDataMap.put(
						tableElement.select("td").text().substring(0,
								tableElement.select("td").text()
										.indexOf(tableElement.select("td").select("Strong").text())),
						tableElement.select("td").select("Strong").text());
			}
			mainDataMap.put(String.valueOf(i), subDataMap);
			i++;
		}
		return mainDataMap;
	}

}
