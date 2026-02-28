package model.Chat;

import org.json.JSONObject;

public class Model_Send_Message {
    private int fromUserID;
    private int toUserID;
    private String text;
    private String time;
    
    public Model_Send_Message(int fromUserID, int toUserID, String text, String time) {
		super();
		this.fromUserID = fromUserID;
		this.toUserID = toUserID;
		this.text = text;
		this.time = time;
	}

	public Model_Send_Message() {
    }


    public JSONObject toJsonObject() {
        try {
            JSONObject json = new JSONObject();
            json.put("type", "sendMessage");
            json.put("fromUserID", fromUserID);
            json.put("toUserID", toUserID);
            json.put("text", text);
            json.put("time", time);
            return json;
        } catch (Exception e) {
            return null;
        }
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

    

}
