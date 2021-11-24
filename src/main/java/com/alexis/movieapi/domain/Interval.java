package com.alexis.movieapi.domain;

import java.util.List;

public class Interval {

	private List<Producer> min;
	private List<Producer> max;

	public List<Producer> getMax() {
		return max;
	}

	public void setMax(List<Producer> max) {
		this.max = max;
	}

	public List<Producer> getMin() {
		return min;
	}

	public void setMin(List<Producer> min) {
		this.min = min;
	}

}
