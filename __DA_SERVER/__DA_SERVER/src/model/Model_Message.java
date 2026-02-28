package model;

import org.json.JSONObject;

public class Model_Message {
    private boolean action;
    private String message;

    public Model_Message(boolean action, String message) {
        this.action = action;
        this.message = message;
    }
    
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("action", action);
			json.put("message", message);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    public Model_Message() {
    }
    
    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
