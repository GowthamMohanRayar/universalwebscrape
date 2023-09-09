package com.web.universalwebscrape.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.web.universalwebscrape.dto.InitiateScrapeDto;
import com.web.universalwebscrape.dto.UrlFromCSVDto;

@Component
public class ScrapeUtils {

	public List<UrlFromCSVDto> doScrapeIfListIsNull(List<UrlFromCSVDto> urlFromCSVDtos, InitiateScrapeDto scrapeDto)
			throws IOException {
		if (null == urlFromCSVDtos) {
			urlFromCSVDtos = Files.lines(Paths.get(scrapeDto.getFileName()))
					.flatMap(word -> Pattern.compile("\n").splitAsStream(word)).parallel().map(UrlFromCSVDto::new)
					.map(urlFromCSVDto -> {
						try {
							Document doc = Jsoup.connect(urlFromCSVDto.getUrl().trim()).get();
							scrapeDto.getTagMapper().entrySet().stream().forEach(mapEntry -> {
								urlFromCSVDto.setDataMap(doc, mapEntry);
							});
						} catch (IOException e) {
						}
						return urlFromCSVDto;
					}).collect(Collectors.toList());
		}
		return urlFromCSVDtos;
	}
}
