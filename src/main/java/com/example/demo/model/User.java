package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "date_of_birth")
	private String dateOfBirth;
	
	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "radius")
	private double radius;

	public User() {
	}
	
	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.dateOfBirth = user.getDateOfBirth();
		this.pictureUrl = user.getPictureUrl();
		this.radius = user.getRadius();
	}
	
	public User(int id, String username, String password,
			String email, String firstName, String lastName,
			String dateOfBirth, String pictureUrl, double radius) {
			this.setId(id);
			this.setUsername(username);
			this.setPassword(password);
			this.setEmail(email);
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setDateOfBirth(dateOfBirth);
			this.setPictureUrl(pictureUrl);
			this.setRadius(radius);
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", pictureUrl=");
		builder.append(pictureUrl);
		builder.append(", radius=");
		builder.append(radius);
		builder.append("]");
		return builder.toString();
	}

}
