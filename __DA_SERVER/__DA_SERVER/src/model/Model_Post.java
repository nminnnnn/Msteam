package model;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Model_Post {
	private int postId;
	private int projectId;
	private String userName;
	private byte[] avatar;
	private String timing;
	private String content;

	public Model_Post(int postId, int projectId, String userName, byte[] avatar, String timing, String content) {
		this.postId = postId;
		this.projectId = projectId;
		this.userName = userName;
		this.avatar = avatar;
		this.timing = timing;
		this.content = content;
	}

	public Model_Post(Object json) {
		JSONObject obj = (JSONObject) json;
		try {
			postId = obj.getInt("postId");
			projectId = obj.getInt("projectId");
			userName = obj.getString("userName");
			avatar = convertHexStringToByteArray(obj.getString("avatar"));
			timing = obj.getString("timing");
			content = obj.getString("content");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject toJsonObject(String type) {
		try {
			JSONObject json = new JSONObject();
			json.put("type", type);
			json.put("postId", postId);
			json.put("projectId", projectId);
			json.put("userName", userName);
			json.put("avatar", convertByteArrayToHexString(avatar));
			json.put("timing", timing);
			json.put("content", content);
//			return json;

			// Chuyển đổi JSONObject thành chuỗi UTF-8
			byte[] bytes = json.toString().getBytes(StandardCharsets.UTF_8);
			String jsonString = new String(bytes, StandardCharsets.UTF_8);

			// Tạo đối tượng JSONObject từ chuỗi UTF-8
			return new JSONObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String convertByteArrayToHexString(byte[] array) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : array) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	private byte[] convertHexStringToByteArray(String hexString) {
		int len = hexString.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
					+ Character.digit(hexString.charAt(i + 1), 16));
		}
		return data;
	}

	public Model_Post() {
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "Model_Post [postId=" + postId + ", projectId=" + projectId + ", userName=" + userName + ", avatar="
				+ Arrays.toString(avatar) + ", timing=" + timing + ", content=" + content + "]";
	}
	
	



}
