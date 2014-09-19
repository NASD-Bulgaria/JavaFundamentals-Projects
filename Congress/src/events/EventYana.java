package events;

import java.util.Date;

/**
 * Event class.
 * @author Yana
 *
 */
public class EventYana {
	private String name;
	private int duration; //integer number of days 
	private Date startDate;
	private Date endDate;
	private String type;
	private String description;
	
	public EventYana(String name, int duration, Date startDate, Date endDate,
			String type, String description) {
		super();
		this.name = name;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.type = type;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
