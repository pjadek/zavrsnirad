package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_by_event")
public class UserByEvent {
	
	@EmbeddedId
	private UserByEventId userByEventId;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "approved")
	private int approved;
	
	public UserByEvent() {}
	
	public UserByEvent(UserByEventId userByEventId, int score, int approved) {
		this.userByEventId = userByEventId;
		this.score = score;
		this.approved = approved;
	}

	public UserByEventId getUserByEventId() {
		return userByEventId;
	}

	public void setUserByEventId(UserByEventId userByEventId) {
		this.userByEventId = userByEventId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserByEvent [userByEventId=");
		builder.append(userByEventId);
		builder.append(", score=");
		builder.append(score);
		builder.append(", approved=");
		builder.append(approved);
		builder.append("]");
		return builder.toString();
	}

}
