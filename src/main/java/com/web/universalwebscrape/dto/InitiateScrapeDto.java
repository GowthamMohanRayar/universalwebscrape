package com.web.universalwebscrape.dto;

import java.util.Map;

public class InitiateScrapeDto {

	private String fileName;
	private Map<String, ElementInfo> tagMapper;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, ElementInfo> getTagMapper() {
		return tagMapper;
	}

	public void setTagMapper(Map<String, ElementInfo> tagMapper) {
		this.tagMapper = tagMapper;
	}

}
