package com.alexis.movieapi.domain;

public class Producer {

	private String name;
	private int interval;
	private int previousWin;
	private int followingWin;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(int previousWin) {
		this.previousWin = previousWin;
	}
	public int getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(int followingWin) {
		this.followingWin = followingWin;
	}
	
}
