package com.web.universalwebscrape.dto;

import com.web.universalwebscrape.constants.InitiateScrapeConstants;

public class RangeDto {
	private double lowerLimit = Double.MAX_VALUE;
	private double upperLimit = Double.MIN_VALUE;

	public void rageCheck(String ipoName, Object valueAsString) {
		double value = InitiateScrapeConstants.STRING_TO_DOUBLE.applyAsDouble(valueAsString);
		if (value < lowerLimit) {
			lowerLimit = value;
		}
		if (value > upperLimit) {
			upperLimit = value;
		}

	}

	public Double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Override
	public String toString() {
		return "RangeDto [lowerLimit=" + lowerLimit + ", upperLimit=" + upperLimit + "]";
	}
}
