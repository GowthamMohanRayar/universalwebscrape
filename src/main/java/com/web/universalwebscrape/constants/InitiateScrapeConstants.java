package com.web.universalwebscrape.constants;

import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

import com.web.universalwebscrape.dto.UrlFromCSVDto;

import io.micrometer.common.util.StringUtils;

public class InitiateScrapeConstants {

	public static final ToDoubleFunction<Object> STRING_TO_DOUBLE = a -> Double
			.valueOf(String.valueOf(a).replaceAll(",", ""));
	public static final String TAG_DELIMITER = ",";
	public static final Predicate<UrlFromCSVDto> CLEAN_DATA_CHECK = dto -> {
		return (cleanDataCheck(dto, "ttm eps") && cleanDataCheck(dto, "p/b") && cleanDataCheck(dto, "cmp")
				&& cleanDataCheck(dto, "beta") && cleanDataCheck(dto, "ttm pe"));
	};
	public static final Predicate<? super UrlFromCSVDto> INTEREST_VALUE_FILTER = dto -> {
		return doubleBetweenCheck(STRING_TO_DOUBLE.applyAsDouble(dto.getDataMap().get("ttm eps")), 15.0, null)
				&& doubleBetweenCheck(STRING_TO_DOUBLE.applyAsDouble(dto.getDataMap().get("p/b")), 3.0, 6.0)
				&& doubleBetweenCheck(STRING_TO_DOUBLE.applyAsDouble(dto.getDataMap().get("cmp")), 0.0, null)
				&& doubleBetweenCheck(STRING_TO_DOUBLE.applyAsDouble(dto.getDataMap().get("beta")), 0.01, 1.00)
				&& doubleBetweenCheck(STRING_TO_DOUBLE.applyAsDouble(dto.getDataMap().get("ttm pe")), 15.0, 30.0);
	};

	private static boolean cleanDataCheck(UrlFromCSVDto dto, String key) {
		return StringUtils.isNotBlank(String.valueOf(dto.getDataMap().get(key)))
				&& !"0.00".equalsIgnoreCase(String.valueOf(dto.getDataMap().get(key)))
				&& !"--".equalsIgnoreCase(String.valueOf(dto.getDataMap().get(key)))
				&& !"null".equalsIgnoreCase(String.valueOf(dto.getDataMap().get(key)));
	}

	private static boolean doubleBetweenCheck(Double valueOf, Double lowerLimit, Double upperLimit) {
		if (null != lowerLimit && null != upperLimit) {
			return valueOf >= lowerLimit && valueOf <= upperLimit;
		} else if (null == lowerLimit && null != upperLimit) {
			return valueOf <= upperLimit;
		} else if (null != lowerLimit && null == upperLimit) {
			return valueOf >= lowerLimit;
		}
		return false;
	}

}
