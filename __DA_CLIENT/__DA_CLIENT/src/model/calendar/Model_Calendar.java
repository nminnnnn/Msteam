package model.calendar;

import org.json.JSONObject;


public class Model_Calendar {
	private int calendar_Id;
	private String title;
	private String day;
	private String timeStart;
	private String timeEnd;
	private String color;
	

	
    public Model_Calendar(int calendar_Id, String title, String day, String timeStart, String timeEnd,
			String color) {
		super();
		this.calendar_Id = calendar_Id;
		this.title = title;
		this.day = day;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.color = color;
	}
    
    public Model_Calendar() {
	}

	public Model_Calendar(Object json) {
        JSONObject obj = (JSONObject) json;
        
        try {
        	calendar_Id = obj.getInt("calendar_Id");
            title = obj.getString("title");
            day = obj.getString("day");
            timeStart = obj.getString("timeStart");
            timeEnd = obj.getString("timeEnd");
            color = obj.getString("color");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("calendar_Id", calendar_Id);
			json.put("title", title);
			json.put("day", day);
			json.put("timeStart", timeStart);
			json.put("timeEnd", timeEnd);
			json.put("color", color);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCalendar_Id() {
		return calendar_Id;
	}

	public void setCalendar_Id(int calendar_Id) {
		this.calendar_Id = calendar_Id;
	}
	
	
	
	
}


