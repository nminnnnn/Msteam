package model.community;

import org.json.JSONObject;

public class Model_Prog {
	private int progId;
	private int projectId;
	private String time;
	private String content;
	
	public Model_Prog(int progId, int projectId, String content, String time) {
		this.progId = progId;
		this.projectId = projectId;
		this.time = time;
		this.content = content;
	}
	
	public Model_Prog(Object json) {
        JSONObject obj = (JSONObject) json;
        try {
        	progId = obj.getInt("progId");
        	projectId = obj.getInt("projectId");
        	time = obj.getString("time");
        	content = obj.getString("content");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("progId", progId);
			json.put("projectId", projectId);
			json.put("time", time);
			json.put("content", content);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	public int getProgId() {
		return progId;
	}

	public void setProgId(int progId) {
		this.progId = progId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
	
	
}
