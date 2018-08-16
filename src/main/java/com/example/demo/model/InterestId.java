package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InterestId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "sport")
	private long sport;
	
	@Column(name = "user")
	private long user;
	
	public InterestId() {}
	
	public InterestId(long sport, long user) {
		this.sport = sport;
		this.user = user;
	}

	public long getSport() {
		return sport;
	}

	public void setSport(long sport) {
		this.sport = sport;
	}

	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}
	
}
