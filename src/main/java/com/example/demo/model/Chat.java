package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
public class Chat {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "event", nullable = false)
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "date_time")
	private String dateTime;
	
	public Chat() {}
	
	public Chat(long id, Event event, User user, String message, String dateTime) {
		this.id = id;
		this.event = event;
		this.user = user;
		this.message = message;
		this.dateTime = dateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Chat [id=");
		builder.append(id);
		builder.append(", event=");
		builder.append(event);
		builder.append(", user=");
		builder.append(user);
		builder.append(", message=");
		builder.append(message);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append("]");
		return builder.toString();
	}

}