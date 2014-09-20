package com.course.congress.datastorage;

import java.io.Serializable;

import com.course.congress.objects.Client;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.objects.Schedule;

public class CongressData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Event[] events;
	private Hall[] halls;
	private Schedule schedules;
	private Client[] clients;
	
	public Event[] getEvents() {
		return events;
	}
	public void setEvents(Event[] events) {
		this.events = events;
	}
	public Hall[] getHalls() {
		return halls;
	}
	public void setHalls(Hall[] halls) {
		this.halls = halls;
	}
	public Schedule getSchedules() {
		return schedules;
	}
	public void setSchedules(Schedule schedules) {
		this.schedules = schedules;
	}
	
}
