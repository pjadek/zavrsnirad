package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sport")
public class Sport {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "picture_url")
	private String pictureUrl;

	public Sport(long id, String name, String pictureUrl) {
		
	}

	public Sport() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sport [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pictureUrl=");
		builder.append(pictureUrl);
		builder.append("]");
		return builder.toString();
	}

}
