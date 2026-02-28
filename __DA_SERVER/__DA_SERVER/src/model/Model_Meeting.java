package model;

import org.json.JSONObject;

public class Model_Meeting {
	private int meetingId;
	private int projectId;
	private String title;
	private String time;
	
	public Model_Meeting(int meetingId, int projectId, String title, String time) {
		this.meetingId = meetingId;
		this.projectId = projectId;
		this.title = title;
		this.time = time;
	}
	
	public Model_Meeting(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	meetingId = obj.getInt("meetingId");
        	projectId = obj.getInt("projectId");
        	title = obj.getString("title");
        	time = obj.getString("time");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("meetingId", meetingId);
			json.put("projectId", projectId);
			json.put("title", title);
			json.put("time", time);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	public int getMeetingId() {
		return meetingId;
	}

	public int getProjectId() {
		return projectId;
	}

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
    
}
