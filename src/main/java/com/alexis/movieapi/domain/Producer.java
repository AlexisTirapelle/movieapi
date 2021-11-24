package com.alexis.movieapi.domain;

public class Producer {

	private String name;
	private int interval;
	private String previousWin;
	private String followingWin;
	
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
	public String getPreviousWin() {
		return previousWin;
	}
	public void setPreviousWin(String previousWin) {
		this.previousWin = previousWin;
	}
	public String getFollowingWin() {
		return followingWin;
	}
	public void setFollowingWin(String followingWin) {
		this.followingWin = followingWin;
	}
	
}
