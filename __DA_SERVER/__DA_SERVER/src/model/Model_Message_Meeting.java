package model;

import org.json.JSONObject;

public class Model_Message_Meeting {
	private int meetingId;
	private int projectId;
	private String name;
	private String message;	
	
	public Model_Message_Meeting(int meetingId, int projectId, String name, String messsage) {
		super();
		this.meetingId = meetingId;
		this.projectId = projectId;
		this.name = name;
		this.message = messsage;
	}
	
    public Model_Message_Meeting(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            meetingId = obj.getInt("meetingId");
            projectId = obj.getInt("projectId");
            name = obj.getString("name");
            message = obj.getString("message");
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
            json.put("name", name);
            json.put("message", message);
            return json;
        } catch (Exception e) {
            return null;
        }
    }
	
	
	public int getMeetingId() {
		return meetingId;
	}
	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String messsage) {
		this.message = messsage;
	}
	
	
}
