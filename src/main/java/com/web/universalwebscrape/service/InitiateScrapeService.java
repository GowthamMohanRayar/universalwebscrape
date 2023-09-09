package com.web.universalwebscrape.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.universalwebscrape.constants.InitiateScrapeConstants;
import com.web.universalwebscrape.dto.InitiateScrapeDto;
import com.web.universalwebscrape.dto.UrlFromCSVDto;
import com.web.universalwebscrape.utils.RangeMapUtils;
import com.web.universalwebscrape.utils.ScrapeUtils;

@Service
public class InitiateScrapeService {

	List<UrlFromCSVDto> urlFromCSVDtos;

	@Autowired
	RangeMapUtils rangeMapUtils;

	@Autowired
	ScrapeUtils scrapeUtils;

	public List<UrlFromCSVDto> initiateScrape(InitiateScrapeDto scrapeDto) {

		try {
			scrapeUtils.doScrapeIfListIsNull(urlFromCSVDtos, scrapeDto);

			// Extract stocks based on custom rule
			urlFromCSVDtos.stream().filter(InitiateScrapeConstants.CLEAN_DATA_CHECK)
					.filter(InitiateScrapeConstants.INTEREST_VALUE_FILTER).forEach(System.out::println);

			//print range of the components for all stocks if file
			rangeMapUtils.printRangeMap(urlFromCSVDtos);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return urlFromCSVDtos;
	}

}
