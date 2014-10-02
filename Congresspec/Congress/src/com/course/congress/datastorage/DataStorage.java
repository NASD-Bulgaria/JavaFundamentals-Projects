package com.course.congress.datastorage;

import java.io.IOException;
import java.util.ArrayList;

import com.course.congress.objects.Equipment;
import com.course.congress.objects.Event;
import com.course.congress.objects.Hall;
import com.course.congress.objects.Schedule;
import com.course.congress.serializable.Serialization;

public class DataStorage {
	
	private static CongressData data = new CongressData();
	
	public static void saveState() throws IOException {
		Serialization.save(data, "save file.ser");
	}
	
	public static void initFromFile(String fileName) throws ClassNotFoundException, IOException {
		data = Serialization.load("save file.ser");
		initData();
	}

	private static void initData() {
		if(data.getEvents() != null)
		for (int i = 0; i < data.getEvents().length; i++) {
			if(data.getEvents()[i].getArrangement() != null) {
				data.getEvents()[i].getArrangement().init();
			}
		}
	}

	public static void setSchedules(Schedule schedule) {
		data.setSchedules(schedule);
	}
	public static Event[] getEvents() {
		return data.getEvents();
	}
	public static Hall[] getHalls() {
		return data.getHalls();
	}
	
	public static ArrayList<Equipment> getEquipments(Event event) {
		Event[] events = data.getEvents();
		for (int i = 0; i < events.length; i++) {
			if(events[i].getID() == event.getID()) {
				return events[i].getEquipments();
			}
		}
		return new ArrayList<Equipment>();
	}

	public static Schedule getSchedule() {
		return data.getSchedules();
	}
	
	public static void addNewHall(Hall hall) {
		Hall[] halls = data.getHalls();
		Hall[] newHalls;
		if(halls != null) {
			newHalls = new Hall[halls.length + 1];						
			for (int i = 0; i < halls.length; i++) {
				newHalls[i] = halls[i];
			}
		} else {
			newHalls = new Hall[1];
		}
		newHalls[newHalls.length - 1] = hall;
		data.setHalls(newHalls);
	}
	
	public static void addNewEvent(Event event) {
		Event[] events = data.getEvents();
		Event[] newEvents;
		if(events != null) {
			newEvents = new Event[events.length + 1];			
			for (int i = 0; i < events.length; i++) {
				newEvents[i] = events[i];
			}
		} else {
			newEvents = new Event[1];
		}
		newEvents[newEvents.length - 1] = event;
		data.setEvents(newEvents);
	}
	
	public static void addNewEquipment(Equipment equipment, int eventID) {
		Event[] events = data.getEvents();
		if(events != null) {
			for (int i = 0; i < events.length; i++) {
				if (events[i].getID() == eventID ) {
					events[i].getEquipments().add(equipment);
				}
			}			
		}
	}

	public static void removeEvent(int id) {
		Event[] newEvents;
		if(data.getEvents().length-1 < 1) {
			newEvents = null;			
		} else {
			newEvents = new Event[data.getEvents().length-1];
		}
		int newEventsCounter = 0;
		for(int i =0; i < data.getEvents().length; i++) {
			if(data.getEvents()[i].getID() == id) {
				
			} else {
				newEvents[newEventsCounter++] = data.getEvents()[i];
			}
		}
		data.setEvents(newEvents);
	}
	
}
