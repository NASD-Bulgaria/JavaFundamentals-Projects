package com.course.congress.datastorage;

import java.io.IOException;

import com.course.congress.objects.Schedule;
import com.course.congress.serializable.Serialization;

public class DataStorage {
	
	private static CongressData data = new CongressData();
	
	public static void saveState() throws IOException {
		Serialization.save(data, "save file.ser");
	}
	
	public static void initFromFile(String fileName) throws ClassNotFoundException, IOException {
		Serialization.load("save file.ser");
	}

	public static void setSchedules(Schedule schedule) {
		data.setSchedules(schedule);
	}

	public static Schedule getSchedule() {
		return data.getSchedules();
	}
}
