package service;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mysql.cj.log.Log;

import model.Model_Calendar;
import model.Model_File;
import model.Model_Login;
import model.Model_Meeting;
import model.Model_Message;
import model.Model_Message_Meeting;
import model.Model_Post;
import model.Model_Prog;
import model.Model_Project;
import model.Model_Receive_Message;
import model.Model_Register;
import model.Model_Send_Message;
import model.Model_User_Account;
import view.Body;
import view.Main;
import view.component.ItemLog;

public class Service {
    private static Service instance;
    private ServerSocket serverSocket;
    public JTextArea textArea = new JTextArea();
    private Main main;
    private final int PORT_NUMBER = 1610;
	private ServiceUser serviceUser;
	private ServiceCommunity serviceCommunity;
	private ServiceCalendar serviceCalendar;
	private ArrayList<ClientHandler> clients = new ArrayList<>();
	public ArrayList<ClientHandler> getClients() {
		return clients;
	}

	private ServiceMessage serviceMessage;
	private static int id = 0;
	
    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
    
    public static Service getInstance(JTextArea textArea) {
        if (instance == null) {
            instance = new Service(textArea);
        }
        return instance;
    }
    
    public static Service getInstance(Main main) {
        if (instance == null) {
            instance = new Service(main);
        }
        return instance;
    }

    
    private Service(JTextArea textArea) {
        this.textArea = textArea;
        serviceUser = new ServiceUser(clients);
    }
    
    private Service(Main main) {
        this.main = main;
        serviceUser = new ServiceUser(clients);
    }
    
    private Service() {
        serviceUser = new ServiceUser(clients);
    }

    
    public void startServer() {
        new Thread(() -> {
            try {
            	serverSocket = new ServerSocket(PORT_NUMBER);
                System.out.println("Server has started on port: " + PORT_NUMBER + "\n");
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("One client connected\n");
                    main.getBody().getLog().logs.add(new ItemLog("Client", "One client connected!"));
                    main.getBody().refresh();
            		main.getBody().showPage(Body.currentPage);
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream() , StandardCharsets.UTF_8));
                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                        InputStream in_image = clientSocket.getInputStream();
                        OutputStream out_image = clientSocket.getOutputStream();
                        ClientHandler clientHandler = new ClientHandler(++id + "",this, in, out,in_image, out_image, clients, clientSocket);
                    }
                    catch (Exception e) {
                    	clientSocket.close();
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
    
//    public void startServer() {
//        new Thread(() -> {
//            try {
//            	serverSocket = new ServerSocket(PORT_NUMBER);
//                textArea.append("Server has started on port: " + PORT_NUMBER + "\n");
//                
//                while (true) {
//                    Socket clientSocket = serverSocket.accept();
//                    textArea.append("One client connected\n");
//                    
//                    try {
//                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream() , StandardCharsets.UTF_8));
//                        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
//                        InputStream in_image = clientSocket.getInputStream();
//                        OutputStream out_image = clientSocket.getOutputStream();
//                        ClientHandler clientHandler = new ClientHandler(++id + "",this, in, out,in_image, out_image, clients, clientSocket);
//                    }
//                    catch (Exception e) {
//                    	clientSocket.close();
//                        e.printStackTrace();
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }
    
    public void listen(ClientHandler client, String newdata) {
		serviceCommunity = new ServiceCommunity(Integer.parseInt(client.getUserId()));
    	serviceCalendar = new ServiceCalendar(Integer.parseInt(client.getUserId()));
		serviceMessage = new ServiceMessage(Integer.parseInt(client.getUserId()));
				
		main.getBody().refresh();
		main.getBody().showPage(Body.currentPage);
    	String data = new String(newdata);
		new Thread(()->{
			try {
    			JSONObject jsonData = new JSONObject(data);
//	            textArea.append("listen: " + jsonData + "\n");
    	    	if(jsonData.getString("type").equals("register")) {
    	            String userName = jsonData.getString("userName");
    	            String password = jsonData.getString("password");
//    	            textArea.append("User has Register :" + userName + " Pass :" + password + "\n");
    	            Model_Register register = new Model_Register(userName, password);
    	            Model_Message message = serviceUser.register(register);
    	            broadcast(client.getUserId(), message.toJsonObject("register"));
    	            if(message.isAction()) {
                        main.getBody().getLog().logs.add(new ItemLog(userName, "Register successfully!"));
                        main.getBody().refresh();
                		main.getBody().showPage(Body.currentPage);
    	            } else {
    	            	main.getBody().getLog().logs.add(new ItemLog(userName, "Register fail!"));
                        main.getBody().refresh();
                		main.getBody().showPage(Body.currentPage);
    	            }
//    	            textArea.append("check Register :" + message.isAction() + "message:" + message.getMessage() );
    	    	}
    	    	else if(jsonData.getString("type").equals("registerInfo")) {
    	    		Model_User_Account user = new Model_User_Account(jsonData);
    	    		serviceUser.registerInfo(user);
    	    	}
    	    	else if(jsonData.getString("type").equals("updateInfo")) {
//    	    		textArea.append("UPDATE INFO :" + jsonData + "\n");
    	    		Model_User_Account user = new Model_User_Account(jsonData);
    	    		serviceUser.updateInfo(user);
    	    		
    	    		main.getBody().getLog().logs.add(new ItemLog(user.getUserName(), "Update infomation"));
                    main.getBody().refresh();
            		main.getBody().showPage(Body.currentPage);
    	    	}
    			else if(jsonData.getString("type").equals("login")) {
    	            String userName = jsonData.getString("userName");
    	            String password = jsonData.getString("password");
//    	            textArea.append("User has Loginnn :" + userName + " Pass :" + password + "\n");
    	            Model_Login login = new Model_Login(userName, password);
    	            Model_Message message = serviceUser.login(login);
    	            broadcast(client.getUserId(), message.toJsonObject("login"));
    	            
    	            if(message.isAction()) {
    	            	
        	            Model_User_Account account = serviceUser.loadLogin();
        	            broadcast(client.getUserId(), account.toJsonObject("loadLogin"));
        	            client.setUserId(account.getUser_Id()+"");
        	         
        	    		main.getBody().getLog().logs.add(new ItemLog(account.getUserName(), "Login successfully!"));
        	            main.getBody().refresh();
        	    		main.getBody().showPage(Body.currentPage);
        	    		        	            
        	            List<Model_Project> list2 = serviceCommunity.getProject(Integer.parseInt(client.getUserId()));
        	            JSONArray jsonArray2 = new JSONArray();
        	            for(Model_Project pro : list2) {    	    
        	            	jsonArray2.put(pro.toJsonObject("listProject"));
//        	            	textArea.append("list project :" +  pro.toJsonObject("listProject") + "\n");
        	            }
        	            JSONObject json2 = new JSONObject();
        	            json2.put("type", "listProject");
        	            json2.put("jsonArray", jsonArray2);
    	            	broadcast(client.getUserId(), json2);
        	            
        	            List<Model_User_Account> list = serviceUser.getUser();
        	            if(list.size() == 0) textArea.append("rong!!!!");
        	            JSONArray jsonArray = new JSONArray();
        	            for(Model_User_Account user : list) { 
        	            	jsonArray.put(user.toJsonObject("list_user"));
        	            	textArea.append("list user :" +  user.toJsonObject("list_user") + "\n");
        	            }
        	            JSONObject json = new JSONObject();
        	            json.put("type", "list_user");
        	            json.put("jsonArray", jsonArray);
    	            	broadcast(client.getUserId(), json);
//        	            client.setUserId(account.getUser_Id()+"");
    	            	
        	            JSONObject jsonActive = new JSONObject();
        	            jsonActive.put("type", "active");
        	            jsonActive.put("userId", account.getUser_Id());
        	            broadcastActive(client.getUserId(), jsonActive);
        	                    	            
        	            textArea.append("\ncheck Login :" + message.isAction() + "message:" + message.getMessage() );    	            
    	            }    	            
    	    	}
    			else if(jsonData.getString("type").equals("sendMessage")) {
    			    int fromUserID = jsonData.getInt("fromUserID");
    			    int toUserID = jsonData.getInt("toUserID");
    			    String text = jsonData.getString("text");
    			    String time = jsonData.getString("time");
    	            textArea.append("Send message form :" + fromUserID + " -to- " + toUserID + " : " + text + "\n");
    	            Model_Send_Message sendMessage = new Model_Send_Message(fromUserID, toUserID, text, time);
    	            serviceMessage.sendMessage(sendMessage);
    	            Model_Receive_Message receiveMessage = new Model_Receive_Message(sendMessage.getFromUserID(), sendMessage.getText(), time);
    	            broadcast(toUserID+"", receiveMessage.toJsonObject("receiveMessage"));
    	            textArea.append("Receive message form :" + fromUserID + " -to- " + toUserID + " : " + text + "\n");
    	    	}
    			else if(jsonData.getString("type").equals("historyMessage")) {
    				int user_Id2 = jsonData.getInt("user_Id2");
	            	textArea.append("historyMessage :" +  client.getUserId() + " - " + user_Id2 +  "\n");
	            	String history = serviceMessage.historyMessage(user_Id2);
    	            broadcastHistory(client.getUserId(), history);    	            
    			}
    			else if(jsonData.getString("type").equals("sendFile")) {
    	            Model_File sendFile = new Model_File(jsonData);
//    	            serviceMessage.sendMessage(sendMessage);
//    	            Model_Message receiveMessage = new Model_Message(jsonData);
    	            broadcast(jsonData.getInt("toUserID") + "", sendFile.toJsonObject("sendFile"));    	 
    	            textArea.append("sendFile: " + sendFile.toJsonObject("sendFile"));
    			}
    			else if(jsonData.getString("type").equals("addProject")) {
    				String projectName = jsonData.getString("projectName");
    				Model_Project project = serviceCommunity.addProject(projectName);
    	            broadcast(client.getUserId(), project.toJsonObject("addProject"));
    	            textArea.append("Add project :" + project.toJsonObject("addProject") + "\n");    
    	            
    	    		main.getBody().getLog().logs.add(new ItemLog("","PROJECT " + project.getProjectName() + " is created"));
    	            main.getBody().refresh();
    	    		main.getBody().showPage(Body.currentPage);
    			}
    			else if(jsonData.getString("type").equals("listProject")) {
    	            List<Model_Project> list2 = serviceCommunity.getProject(Integer.parseInt(client.getUserId()));
    	            JSONArray jsonArray2 = new JSONArray();
    	            for(Model_Project pro : list2) {    	    
    	            	jsonArray2.put(pro.toJsonObject("listProject"));
    	            	textArea.append("list project :" +  pro.toJsonObject("listProject") + "\n");
    	            }
    	            JSONObject json2 = new JSONObject();
    	            json2.put("type", "listProject");
    	            json2.put("jsonArray", jsonArray2);
	            	broadcast(client.getUserId(), json2);
    	            textArea.append("list project DONE \n");
    			}
    			else if(jsonData.getString("type").equals("postNews")) {
    				Model_Post post = new Model_Post(jsonData);
    				serviceCommunity = new ServiceCommunity(Integer.parseInt(client.getUserId()));
    				Model_Post post1 = serviceCommunity.postNews(post);
    				broadcastCommunity(post.getProjectId(), post1.toJsonObject("postNews"));
    	            textArea.append("Post new :" + post1.toJsonObject("postNews") + "\n");
    			}
    			else if(jsonData.getString("type").equals("listPost")) {
	            	textArea.append("list post :" +  jsonData + "\n");
	            	textArea.append("project id :" +  jsonData.getInt("projectId") + "\n");
    	            List<Model_Post> list = serviceCommunity.getPost(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!\n");
    	            JSONArray jsonArray = new JSONArray();
    	            for(Model_Post post : list) {   
    	            	jsonArray.put(post.toJsonObject("listPost"));
    	            	textArea.append("list post :" +  post.toJsonObject("listPost") + "\n");
    	            }
    	            JSONObject json = new JSONObject();
    	            json.put("type", "listPost");
    	            json.put("jsonArray", jsonArray);
	            	broadcast(client.getUserId(), json);
    	            textArea.append("list post DONE \n");
    			}
    			else if(jsonData.getString("type").equals("listProg")) {
	            	textArea.append("list Prog :" +  jsonData + "\n");
	            	textArea.append("project id :" +  jsonData.getInt("projectId") + "\n");
    	            List<Model_Prog> list = serviceCommunity.getProg(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!\n");
    	            JSONArray jsonArray = new JSONArray();
    	            for(Model_Prog prog : list) {   
    	            	jsonArray.put(prog.toJsonObject("listProg"));
    	            	textArea.append("list prog :" +  prog.toJsonObject("listProg") + "\n");
    	            }
    	            JSONObject json = new JSONObject();
    	            json.put("type", "listProg");
    	            json.put("jsonArray", jsonArray);
	            	broadcast(client.getUserId(), json);
    	            textArea.append("list prog DONE \n");
    			}
    			else if(jsonData.getString("type").equals("newProg")) {
    				Model_Prog prog = new Model_Prog(jsonData);
    				serviceCommunity = new ServiceCommunity(Integer.parseInt(client.getUserId()));
    				Model_Prog prog1 = serviceCommunity.newProg(prog);
    				broadcastCommunity(prog1.getProjectId(), prog1.toJsonObject("newProg"));
    	            textArea.append("Post new :" + prog1.toJsonObject("newProg") + "\n");
    			}
    			else if(jsonData.getString("type").equals("addMeeting")) {
    				Model_Meeting meeting = serviceCommunity.addMeeting(new Model_Meeting(jsonData));    	               	            
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	            for(Model_User_Account user : list) {  
        	            broadcast(user.getUser_Id() + "", meeting.toJsonObject("addMeeting"));
        	            textArea.append("Add meeting :" + meeting.toJsonObject("addMeeting") + "\n");
    	            };
    	            
    	            main.getBody().getLog().logs.add(new ItemLog("","MEETING " + meeting.getMeetingId() + "-" + meeting.getTitle() + " is created"));
    	            main.getBody().refresh();
    	    		main.getBody().showPage(Body.currentPage);
    			}
    			else if(jsonData.getString("type").equals("listMeeting")) {
    	            List<Model_Meeting> list = serviceCommunity.getMeeting(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!\n");
//    	            for(Model_Post post : list) {    	    
//    	            	broadcast(client.getUserId(), post.toJsonObject("listPost"));
//    	            	textArea.append("list post :" +  post.toJsonObject("listPost") + "\n");
//    	            }
//    	            textArea.append("list project DONE \n");
    	            
    	            JSONArray jsonArray = new JSONArray();
    	            for (Model_Meeting meeting : list) {
    	                jsonArray.put(meeting.toJsonObject("listMeeting"));
    	            }
    	            
    	            JSONObject json = new JSONObject();
    	            json.put("type", "listMeeting");
    	            json.put("jsonArray", jsonArray);
    	            
	            	broadcast(client.getUserId(), json);
	            	textArea.append("list meeting DONE \n");
    			}
    			else if(jsonData.getString("type").equals("listMember")) {
	            	textArea.append("list member :" +  jsonData + "\n");
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	            for(Model_User_Account user : list) {    	    
    	            	broadcastCommunity(jsonData.getInt("projectId"), user.toJsonObject("listMember"));
    	            	textArea.append("list member :" +  user.toJsonObject("listMember") + "\n");
    	            };
    			}
    			else if(jsonData.getString("type").equals("addMember")) {
    				String userName = jsonData.getString("userName");
    				int projectId = jsonData.getInt("projectId");
    				Model_User_Account user = serviceCommunity.addMember(userName, projectId);
    				broadcastCommunity(jsonData.getInt("projectId"), user.toJsonObject("addMember"));
    			}
    			else if(jsonData.getString("type").equals("addCalendar")) {
    				String title = jsonData.getString("title");
    				String day = jsonData.getString("day");
    				String timeStart = jsonData.getString("timeStart");
    				String timeEnd = jsonData.getString("timeEnd");
    				String color = jsonData.getString("color");
    				Model_Calendar item = serviceCalendar.addCalendar(title, day, timeStart, timeEnd, color);
    	            textArea.append("Add calendar :" + item.toJsonObject("addCalendar") + "\n");
    			}
    			else if(jsonData.getString("type").equals("listCalendar")) {
	            	textArea.append("list calendar :" +  jsonData + "\n");	            	            	
    	            List<Model_Calendar> list = serviceCalendar.getCalendar(Integer.parseInt(client.getUserId()));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	            JSONArray jsonArray = new JSONArray();
    	            for(Model_Calendar item : list) {  
    	            	jsonArray.put(item.toJsonObject("listCalendar"));
    	            	textArea.append("list project :" +  item.toJsonObject("listProject") + "\n");
    	            }    	            
    	            JSONObject json = new JSONObject();
    	            json.put("type", "listCalendar");
    	            json.put("jsonArray", jsonArray);
	            	broadcast(client.getUserId(), json);
    	            textArea.append("list calendar DONE \n");
    			}
    			else if(jsonData.getString("type").equals("openMeeting")) {
	            	int meetingId = jsonData.getInt("meetingId");
	            	openMeeting(meetingId);
    			}
    			else if(jsonData.getString("type").equals("joinMeeting")) {
	            	textArea.append("list member in meeting :" +  jsonData + "\n");
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "joinMeeting");
    	    			json.put("meetingId", jsonData.getInt("meetingId"));
    	    			json.put("projectId", jsonData.getInt("projectId"));
    	    			json.put("userId", jsonData.getInt("userId"));
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}
    	            for(Model_User_Account user : list) {    	    
    	            	broadcastMessage(user.getUser_Id()+"", json);
    	            	textArea.append("new user join :" +  json + "\n");
    	            };
    			}
    			else if(jsonData.getString("type").equals("joinedMeeting")) {
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "joinedMeeting");
    	    			json.put("userId", jsonData.getInt("userId"));
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		} 	    
    	            	broadcastMessage(jsonData.getInt("newUserId") + "", json);
    			}
    			else if(jsonData.getString("type").equals("leaveMeeting")) {
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "leaveMeeting");
    	    			json.put("meetingId", jsonData.getInt("meetingId"));
    	    			json.put("projectId", jsonData.getInt("projectId"));
    	    			json.put("userId", jsonData.getInt("userId"));
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}
    	            for(Model_User_Account user : list) {    	    
    	            	broadcastMessage(user.getUser_Id()+"", json);
    	            	textArea.append("new user leave :" +  json + "\n");
    	            };
    			}
    			else if(jsonData.getString("type").equals("stopShare")) {
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	        	JSONObject json = new JSONObject();
    	    		try {
    	    			json.put("type", "stopShare");
    	    			json.put("meetingId", jsonData.getInt("meetingId"));
    	    			json.put("projectId", jsonData.getInt("projectId"));
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}
    	            for(Model_User_Account user : list) {    	    
    	            	broadcastMessage(user.getUser_Id()+"", json);
    	            };
    			}
    			else if(jsonData.getString("type").equals("messageMeeting")) {
    	            List<Model_User_Account> list = serviceCommunity.getMember(jsonData.getInt("projectId"));
    	            if(list.size() == 0) textArea.append("rong!!!!");
    	            Model_Message_Meeting message = new Model_Message_Meeting(jsonData);
    	            for(Model_User_Account user : list) {
    	            	if(user.getUser_Id() != Integer.parseInt(client.getUserId())) {
        	            	broadcastMessage(user.getUser_Id()+"", message.toJsonObject("messageMeeting"));
    	            	}
    	            };
    			}

    		} catch (JSONException e) {
    			textArea.append("server nhan: " + data + "\n");
    			e.printStackTrace();
    		}
    	}).start();
    }
    
    public void listenImage(BufferedImage img) {
    	textArea.append("server image nhan: " + img.toString() + "\n");
        broadcastImage(img);
    }

    public void broadcastImage(BufferedImage img) {
        try {
            for (ClientHandler client : clients) {
                client.sendImage(img);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void broadcast(String userId, JSONObject jsonData) {
//    	new Thread(()-> {
            for (ClientHandler client : clients) {
                if(client.getUserId().equals(userId)) {
                	client.sendMessage(jsonData);
                }
            }
//    	}).start();
    }
    
    public void broadcastHistory(String userId, String history) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "historyMessage");
			json.put("history", history);
		} catch (Exception e) {
			e.printStackTrace();
		}
//    	new Thread(()-> {
            for (ClientHandler client : clients) {
                if(client.getUserId().equals(userId)) {
                	client.sendMessage(json);
                }
            }
//    	}).start();
    }
    
    public void broadcastMessage(String userId, JSONObject jsonData) {
//    	new Thread(()-> {
            for (ClientHandler client : clients) {
                if(client.getUserId().equals(userId)) {
                	client.sendMessage(jsonData);
                }
            }
//    	}).start();
    }
	
    public void broadcastCommunity(int projectId, JSONObject jsonData) {
    	List<Model_User_Account> list = new ServiceCommunity(1).getMember(projectId);
//    	new Thread(()-> {
            for (ClientHandler client : clients) {
                for(Model_User_Account account : list) {
                	if(client.getUserId().equals(account.getUser_Id()+"")) {
                		client.sendMessage(jsonData);
                		break;
                	}
                }
            }
//    	}).start();
    }
    
    public void broadcastActive(String userId, JSONObject jsonData) {
//    	new Thread(()-> {
            for (ClientHandler client : clients) {
                if(!client.getUserId().equals(userId)) {
                	client.sendMessage(jsonData);
                }
            }
//    	}).start();
    }
    
    public void openMeeting(int meetingId) {
        new Thread(() -> {
            int UDP_PORT = meetingId;
            DatagramSocket audioSocket;
            DatagramPacket audioPacket;
            SourceDataLine audio_out;
        	SourceDataLine audioOut;
        	try {
    			AudioFormat format = getaudioformat();
    			DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format);
    			if (!AudioSystem.isLineSupported(info_out)) {
    				System.out.println("Not support");
    				System.exit(0);
    			}
    			audioOut = (SourceDataLine) AudioSystem.getLine(info_out);
    			audioOut.open(format);
    			audioOut.start();
    			
    			audioSocket = new DatagramSocket(UDP_PORT);
    			textArea.append("Start UPD with PORT " + UDP_PORT + "\n");
//    			audioSocket = new DatagramSocket();

    			audio_out = audioOut;
    			
    			byte[] buffer = new byte[512];
    	        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
    	        while (true) {
    	            int i = 0;
    	            try {
    	            	audioSocket.receive(incoming);
    	                buffer = incoming.getData();
//    	                broadcastAudio(buffer);
    	                
    	        	    try {
    	        	        for (ClientHandler client : clients) {
    	        	            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, client.getSocket().getInetAddress(), Integer.parseInt(client.getUserId()));
    	        	            audioSocket.send(packet);   
//    	        	            textArea.append("Audio data sent to: " + client.getSocket().getInetAddress() + " port: 7777" + client.getSocket().getPort() + "\n");
//    	        	            textArea.append(audioData+"\n");
    	        	        }
    	        	    } catch (IOException e) {
    	        	        e.printStackTrace();
    	        	    }
    	                
    	                
//    	                audio_out.write(buffer, 0, buffer.length);
//    	                textArea.append("#" + i++);
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	            }
    	        }
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }).start();
        
        new Thread(() -> {
            int UDP_PORT = meetingId + 1000;
            DatagramSocket audioSocket;
            DatagramPacket audioPacket;
        	try {   			
    			audioSocket = new DatagramSocket(UDP_PORT);
    			textArea.append("Start UPD with PORT " + UDP_PORT + "\n");
    			
    			byte[] buffer = new byte[65507];
    	        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
    	        while (true) {
    	            int i = 0;
    	            try {
    	            	audioSocket.receive(incoming);
    	                buffer = incoming.getData();
//    	                broadcastAudio(buffer);
    	                
    	        	    try {
    	        	        for (ClientHandler client : clients) {
    	        	            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, client.getSocket().getInetAddress(), Integer.parseInt(client.getUserId()) + 1000);
    	        	            audioSocket.send(packet);   
//    	        	            textArea.append("Audio data sent to: " + client.getSocket().getInetAddress() + " port: 7777" + client.getSocket().getPort() + "\n");
//    	        	            textArea.append(audioData+"\n");
    	        	        }
    	        	    } catch (IOException e) {
    	        	    	e.printStackTrace();
    	        	    	break;
    	        	    }
    	                
    	                
//    	                audio_out.write(buffer, 0, buffer.length);
//    	                textArea.append("#" + i++);
    	            } catch (IOException e) {
    	                e.printStackTrace();
    	                break;
    	            }
    	        }
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }).start();
    }
    
	public static AudioFormat getaudioformat() {
		float sampleRate = 8000.0F;
		int sampleSizeInbits = 16;
		int channel = 2;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInbits, channel, signed, signed);
	}

	public Main getMain() {
		return main;
	}
	
//	public void broadcastAudio(byte[] audioData) {
//	    try {
//	        for (ClientHandler client : clients) {
//	            DatagramPacket packet = new DatagramPacket(audioData, audioData.length, client.getSocket().getInetAddress(), UDP_PORT_CLIENT);
//	            audioSocket.send(packet);   
////	            textArea.append("Audio data sent to: " + client.getSocket().getInetAddress() + " port: 7777" + client.getSocket().getPort() + "\n");
////	            textArea.append(audioData+"\n");
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
	
	
}
