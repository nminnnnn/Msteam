package model.community;

public class Model_Community {
	private int communityId;
	private int projectId;
	private int userId;
	
	public Model_Community(int communityId, int projectId, int userId) {
		this.communityId = communityId;
		this.projectId = projectId;
		this.userId = userId;
	}
	
	public Model_Community() {
	}

	public int getCommunityId() {
		return communityId;
	}

	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
