package com.example.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "interest")
public class Interest {

	@EmbeddedId
	private InterestId interestId;
	
	public Interest() {}
	
	public Interest(InterestId interestId) {
		this.interestId = interestId;
	}

	public InterestId getInterestId() {
		return interestId;
	}

	public void setInterestId(InterestId interestId) {
		this.interestId = interestId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Interest [interestId=");
		builder.append(interestId);
		builder.append("]");
		return builder.toString();
	}
	
}
