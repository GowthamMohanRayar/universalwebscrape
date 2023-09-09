package com.web.universalwebscrape.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.web.universalwebscrape.constants.InitiateScrapeConstants;
import com.web.universalwebscrape.dto.RangeDto;
import com.web.universalwebscrape.dto.UrlFromCSVDto;

@Component
public class RangeMapUtils {

	public void printRangeMap(List<UrlFromCSVDto> urlFromCSVDtos) {
		RangeDto ttm_eps = new RangeDto();
		RangeDto pbRatio = new RangeDto();
		RangeDto cmp = new RangeDto();
		RangeDto beta = new RangeDto();
		RangeDto ttm_pe = new RangeDto();
		Map<String, RangeDto> rangeMap = new HashMap<>();
		rangeMap.put("ttm_eps", ttm_eps);
		rangeMap.put("pbRatio", pbRatio);
		rangeMap.put("cmp", cmp);
		rangeMap.put("beta", beta);
		rangeMap.put("ttm_pe", ttm_pe);
		urlFromCSVDtos.stream().filter(InitiateScrapeConstants.CLEAN_DATA_CHECK).forEach(dto -> {
			ttm_eps.rageCheck(dto.getDataMap().get("ttm eps"));
			pbRatio.rageCheck(dto.getDataMap().get("p/b"));
			cmp.rageCheck(dto.getDataMap().get("cmp"));
			beta.rageCheck(dto.getDataMap().get("beta"));
			ttm_pe.rageCheck(dto.getDataMap().get("ttm pe"));
		});
		System.out.println(rangeMap);
	}

}
