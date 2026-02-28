package model;

public class Model_Project_Manager {
	private int projectId;
	private String projectName;
	private int totalUsers;
	private int totalMeetings;
	private int totalPosts;
	
	
	public Model_Project_Manager(int projectId, String projectName, int totalUsers, int totalMeetings, int totalPosts) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.totalUsers = totalUsers;
		this.totalMeetings = totalMeetings;
		this.totalPosts = totalPosts;
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


	public int getTotalUsers() {
		return totalUsers;
	}


	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}


	public int getTotalMeetings() {
		return totalMeetings;
	}


	public void setTotalMeetings(int totalMeetings) {
		this.totalMeetings = totalMeetings;
	}


	public int getTotalPosts() {
		return totalPosts;
	}


	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
	}
	
	
}
