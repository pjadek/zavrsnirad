package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserByEventId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "user")
	private long user;

	@Column(name = "event")
	private long event;
	
	public UserByEventId() {}

	public UserByEventId(long user, long event) {
		this.user = user;
		this.event = event;
	}
	
	public long getUser() {
		return user;
	}

	public void setUser(long user) {
		this.user = user;
	}

	public long getEvent() {
		return event;
	}

	public void setEvent(long event) {
		this.event = event;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserByEventId [user=");
		builder.append(user);
		builder.append(", event=");
		builder.append(event);
		builder.append("]");
		return builder.toString();
	}
}
