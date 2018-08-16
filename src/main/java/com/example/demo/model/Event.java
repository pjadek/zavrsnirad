package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "event")
public class Event {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date")
	private String date;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "latitude")
	private double latitude;
	
	@Column(name = "minimum_players")
	private int minimumPlayers;
	
	@Column(name = "maximum_players")
	private int maximumPlayers;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "sport", nullable = false)
	private Sport sport;
	
	@ManyToOne
	@JoinColumn(name = "organisator", nullable = false)
	private User user;
	
	public Event() {}

	public Event(long id, String name, String date, 
					String time, double lon, double lat, 
					int min, int max, String desc,
					String address, Sport sport, User user) {
		this.id = id;
		this.name = name;
		this.date = date;
		this.time = time;
		this.longitude = lon;
		this.latitude = lat;
		this.minimumPlayers = min;
		this.maximumPlayers = max;
		this.description = desc;
		this.address = address;
		this.sport = sport;
		this.user = user;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getMinimumPlayers() {
		return minimumPlayers;
	}

	public void setMinimumPlayers(int minimumPlayers) {
		this.minimumPlayers = minimumPlayers;
	}

	public int getMaximumPlayers() {
		return maximumPlayers;
	}

	public void setMaximumPlayers(int maximumPlayers) {
		this.maximumPlayers = maximumPlayers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", date=");
		builder.append(date);
		builder.append(", time=");
		builder.append(time);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", minimumPlayers=");
		builder.append(minimumPlayers);
		builder.append(", maximumPlayers=");
		builder.append(maximumPlayers);
		builder.append(", description=");
		builder.append(description);
		builder.append(", address=");
		builder.append(address);
		builder.append(", sport=");
		builder.append(sport);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
	

}