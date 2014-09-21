package com.course.congress.objects;

import java.io.Serializable;

public class Hall implements Serializable {
	
	private String ID;
	private String name;
	private int capacity;
	private int floor;
	
	public Hall(String iD, String name, int capacity, int floor) {
		super();
		ID = iD;
		this.name = name;
		this.capacity = capacity;
		this.floor = floor;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
}
