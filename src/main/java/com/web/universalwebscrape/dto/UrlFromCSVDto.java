package com.web.universalwebscrape.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.web.universalwebscrape.constants.InitiateScrapeConstants;
import com.web.universalwebscrape.utils.DataLookupUtils;

public class UrlFromCSVDto {

	private String ipoName;
	private String url;
	private Map<String, Object> dataMap;

	public UrlFromCSVDto(String lineFromCsv) {
		super();
		String[] lineFromCsvSplitByComma = lineFromCsv.split(",");
		this.ipoName = lineFromCsvSplitByComma[0];
		this.url = lineFromCsvSplitByComma[1];
		dataMap = new HashMap<>();
	}

	public String getIpoName() {
		return ipoName;
	}

	public String getUrl() {
		return url;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public String toString() {
//		return "UrlFromCSVDto [ipoName=" + ipoName + ", url=" + url + ", dataMap=" + dataMap + "]";
		return ipoName + "^" + String.valueOf(dataMap.get("ttm eps")).replaceAll(",", "") + "^"
				+ String.valueOf(dataMap.get("p/b")).replaceAll(",", "") + "^"
				+ String.valueOf(dataMap.get("cmp")).replaceAll(",", "") + "^"
				+ String.valueOf(dataMap.get("beta")).replaceAll(",", "") + "^"
				+ String.valueOf(dataMap.get("ttm pe")).replaceAll(",", "");
	}

	public void setDataMap(Document doc, Entry<String, ElementInfo> mapEntry) {
		if ("text".equalsIgnoreCase(mapEntry.getValue().getElementType())) {
			dataMap.put(mapEntry.getKey(),
					getValueFromDoc(doc, mapEntry.getValue().getTag().split(InitiateScrapeConstants.TAG_DELIMITER))
							.text());
		} else {
			dataMap.put(mapEntry.getKey(), getValueFromDocByObjectType(doc,
					mapEntry.getValue().getTag().split(InitiateScrapeConstants.TAG_DELIMITER), mapEntry.getKey()));
		}
	}

	private Object getValueFromDocByObjectType(Document doc, String[] tags, String key) {
		Elements parentElement = getValueFromDoc(doc, tags);
		if (parentElement.text().contains("No Data For Insider Transaction Summary")) {
			return parentElement.text();
		} else {
			if ("insider".equalsIgnoreCase(key)) {
				return DataLookupUtils.extractInsiderData(parentElement);
			} else if ("beta".equalsIgnoreCase(key)) {
				return DataLookupUtils.extractBeta(parentElement);
			} else {
				return "";
			}
		}
	}

	private Elements getValueFromDoc(Document doc, String[] tags) {
		Elements element = doc.select(tags[0]);
		for (int i = 1; i < tags.length; i++) {
			element = element.select(tags[i]);
		}
		return element;
	}

}
