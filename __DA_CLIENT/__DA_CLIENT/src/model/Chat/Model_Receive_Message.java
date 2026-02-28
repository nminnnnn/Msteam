package model.Chat;

import org.json.JSONObject;

public class Model_Receive_Message {
    private int fromUserID;
    private String text;
    private String time;

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    

    public Model_Receive_Message(int fromUserID, String text, String time) {
		super();
		this.fromUserID = fromUserID;
		this.text = text;
		this.time = time;
	}

	public Model_Receive_Message(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
            fromUserID = obj.getInt("fromUserID");
            text = obj.getString("text");
            time = obj.getString("time");
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("fromUserID", fromUserID);
            json.put("text", text);
            json.put("time", time);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
    
    
}
