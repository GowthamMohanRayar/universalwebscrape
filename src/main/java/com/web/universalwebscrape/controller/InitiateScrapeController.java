package com.web.universalwebscrape.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.universalwebscrape.dto.InitiateScrapeDto;
import com.web.universalwebscrape.dto.UrlFromCSVDto;
import com.web.universalwebscrape.service.InitiateScrapeService;

@RestController
public class InitiateScrapeController {

	@Autowired
	InitiateScrapeService initiScrapeService;

	@PostMapping(path = "/initiate/scrape", consumes = "application/json", produces = "application/json")
	public List<UrlFromCSVDto> initiateScrape(@RequestBody InitiateScrapeDto scrapeDto) {
		return initiScrapeService.initiateScrape(scrapeDto);
	}

}
