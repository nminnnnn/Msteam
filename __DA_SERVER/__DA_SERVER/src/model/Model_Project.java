package model;

import org.json.JSONObject;

public class Model_Project {
	private int projectId;
	private String projectName;
	
	public Model_Project(int projectId, String projectName) {
		this.projectId = projectId;
		this.projectName = projectName;
	}

	public Model_Project() {
		
	}
	
    public JSONObject toJsonObject(String type) {
    	try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("projectId", projectId);
			json.put("projectName", projectName);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
}
