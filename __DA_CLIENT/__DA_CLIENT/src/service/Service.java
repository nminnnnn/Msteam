package service;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Chat.Model_File;
import model.Chat.Model_Message;
import model.Chat.Model_Receive_Message;
import model.Chat.Model_User_Account;
import model.calendar.Model_Calendar;
import model.community.Model_Meeting;
import model.community.Model_Message_Meeting;
import model.community.Model_Post;
import model.community.Model_Prog;
import model.community.Model_Project;
import view.MainUI;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.form.Login;

public class Service {
	// ========== CẤU HÌNH KẾT NỐI SERVER (chạy trên 2 máy khác nhau) ==========
	// Đổi thành IP của máy đang chạy SERVER (VD: "192.168.1.100")
	// Giữ "localhost" nếu server và client chạy cùng 1 máy
	private static final String SERVER_IP = "localhost";
	
	private static Service instance;
	private Socket client;
	private final int PORT_NUMBER = 1610;
	private final String IP = SERVER_IP;
	private Model_User_Account user;
	private BufferedReader in;
	private DataOutputStream out;
    private InputStream in_image;
    private OutputStream out_image;
	private Model_Message model_message;
	private Login login;
	private MainUI main;
	public volatile boolean on_mic = true;
	public volatile boolean on_loa = true;
	private volatile boolean on_img = false;
	private Thread thread_loa;
	private Thread thread_mic;
	private Thread thread_img;
	
	public static Service getInstance(MainUI main) {
		if(instance == null) {
			instance = new Service(main);
		}
		return instance;
	}
	
    public static Service getInstance() {
    	return instance;
    }
	
	private Service(MainUI main) {
		this.main = main;
	}
	
    public void startClient(){
    	try {
        	client = new Socket(IP, PORT_NUMBER);
            in = new BufferedReader(new InputStreamReader(client.getInputStream() , StandardCharsets.UTF_8));
            out = new DataOutputStream(client.getOutputStream());
            in_image = client.getInputStream();
            out_image = client.getOutputStream();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
        new Thread(() -> {
            while (true) {
                try {
//                    String message = in.readLine(); 
                	
                    String message;
                    synchronized (in) {
                        message = in.readLine();
                    }
                    
                    if (message != null) {
//                        System.out.println("client: " + message + "\n");
                        listen(message);
                    } else {
                        System.out.println("Client disconnected");
                        break;
                    }
                	
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        
//        new Thread(() -> {
//            while (true) {
//                try {
////                    BufferedImage img = ImageIO.read(in_image); 
//                    BufferedImage img;
//                    synchronized (in_image) {
//                        img = ImageIO.read(in_image);
//                    }
//                    listenImage(img);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        
        
    	

//    	new Thread(()->{
//    		boolean checkImg = true;
//    		boolean checkJson = true;
//            while (true) {
//            	if(checkJson) {
//                    try {
//                        String message = in.readLine();
//                        if(message != null) {
//                        	listen(message);
//                        	checkImg = false;
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.out.println("đóng clientHandler");
////                        break;
//                    }
//            	}
//
//            	if(checkImg) {
//	              try {
//	                  BufferedImage img = ImageIO.read(in_image);
//	                  listenImage(img);
//	                  checkJson = false;
//	              } catch (Exception e) {
//	                  e.printStackTrace();
//	                  break;
//	              }
//            	}
//            	checkImg = true;
//            }
//    	}).start();
    	
        
    }
    
    public void listen(String newdata) {
    	JSONObject jsonData;
    	String data=new String(newdata);
		try {
			jsonData = new JSONObject(data);
//			System.out.println("server: " + jsonData.toString() + "\n");
	    	if(jsonData.getString("type").equals("register")) {
	            String message = jsonData.getString("message");
	            boolean action = jsonData.getBoolean("action");
	            model_message = new Model_Message(action, message);	 
	            Service.getInstance().getMain().getLogin().getRegister().checkRegister();      
	    	}
	    	else if(jsonData.getString("type").equals("login")) {
	            String message = jsonData.getString("message");
	            boolean action = jsonData.getBoolean("action");
	            model_message = new Model_Message(action, message);	 
	            Service.getInstance().getMain().getLogin().getLogin().checkLogin();   
	    	}
	    	else if(jsonData.getString("type").equals("loadLogin")) {
//	    		System.out.println(jsonData);
	            user = new Model_User_Account(jsonData); 
	            Service.getInstance().getMain().setUser(user);
	            main.loadUser(user);
	    	}
	    	else if(jsonData.getString("type").equals("list_user")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_User_Account list_user = new Model_User_Account(json);
		    		if(!list_user.getUserName().equals(user.getUserName())) {
		    			PublicEvent.getInstance().getEventMenuLeft().newUser(list_user);
		    		}	            
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("receiveMessage")) {
                Model_Receive_Message message = new Model_Receive_Message(jsonData);
                PublicEvent.getInstance().getEventChat().receiveMessage(message);
	    	}
	    	else if(jsonData.getString("type").equals("historyMessage")) {
                String history = jsonData.getString("history");
                main.getHome().getChat().getChatBody().loadHistory(history);
	    	}
	    	else if(jsonData.getString("type").equals("sendFile")) {
                Model_File file = new Model_File(jsonData);
//                main.getHome().getChat().getChatBody().addItemLeft(file);
                PublicEvent.getInstance().getEventChat().receiveMessage(file);
	    	}
	    	else if(jsonData.getString("type").equals("addProject")) {
	    		Model_Project project = new Model_Project(jsonData);
	    		main.getHome_community().getMenuLeft().addProject(project);
	    	}
	    	else if(jsonData.getString("type").equals("listProject")) {	    	
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Project project = new Model_Project(json);
	                main.getHome_community().getMenuLeft().addProject(project);
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("postNews")) {
	    		Model_Post post = new Model_Post(jsonData);
	    		main.getHome_community().getBody().getPage().getNews().post(post);
	    		main.getHome_community().getBody().getPage().updateScroll();
	    	}
	    	else if(jsonData.getString("type").equals("newProg")) {
	    		Model_Prog prog = new Model_Prog(jsonData);
	    		main.getHome_community().getBody().getPage().getProgs().addProg(prog);
	    		main.getHome_community().getBody().getPage().updateScroll();
	    	}
	    	else if(jsonData.getString("type").equals("listPost")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Post post = new Model_Post(json);
	                main.getHome_community().getBody().getPage().getNews().post(post);
	                main.getHome_community().getBody().getPage().updateScroll();
	            }
	    	}
	    	else if(jsonData.getString("type").equals("listProg")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Prog prog = new Model_Prog(json);
	                main.getHome_community().getBody().getPage().getProgs().addProg(prog);	            
	            }
	            main.getHome_community().getBody().getPage().updateScroll();
	    	}
	    	else if(jsonData.getString("type").equals("listMember")) {
	    		Model_User_Account user = new Model_User_Account(jsonData);
	    		main.getHome_community().getBody().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("addMember")) {
	    		Model_User_Account user = new Model_User_Account(jsonData);
	    		main.getHome_community().getBody().getMember().addMember(user);
	    	}
	    	else if(jsonData.getString("type").equals("listCalendar")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Calendar item = new Model_Calendar(json);
	                main.getCalendarUI().addCalendarFromServer(item);
		    	}
	    	}
	    	else if(jsonData.getString("type").equals("addMeeting")) {
	    		Model_Meeting meeting = new Model_Meeting(jsonData);
	    		main.getHome_community().getBody().getPage().getMeets().addMeet(meeting);
	    		main.getHome_community().getBody().getPage().updateScroll();
	    	}
	    	else if(jsonData.getString("type").equals("listMeeting")) {
	    		JSONArray jsonArray = jsonData.getJSONArray("jsonArray");
	    		List<Model_Meeting> list = new ArrayList<>();
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject json = jsonArray.getJSONObject(i);
	                Model_Meeting meeting = new Model_Meeting(json);
		    		main.getHome_community().getBody().getPage().getMeets().addMeet(meeting);
	            }
	            main.getHome_community().getBody().getPage().updateScroll();
	    	}
	    	else if(jsonData.getString("type").equals("active")) {
	    		int id = jsonData.getInt("userId");
	    		main.getHome().loadActive(id, true);
	    	}
	    	else if(jsonData.getString("type").equals("noActive")) {
	    		int id = jsonData.getInt("userId");
	    		main.getHome().loadActive(id, false);
	    	}
	    	else if(jsonData.getString("type").equals("joinMeeting")) {   				
    			if(main.getHome_community().getMeeting_room().getMeetingId() == jsonData.getInt("meetingId")) {
    				Model_User_Account user = main.getHome().getMenu_Left().getUser(jsonData.getInt("userId"));
    				user.setStatus(true);
    				main.getHome_community().getMeeting_room().getMenuLeft().newUser(user);
    				
    				joinedMeeting(Service.getInstance().getUser().getUser_Id(), jsonData.getInt("userId"));
    			}
	    	}
	    	else if(jsonData.getString("type").equals("joinedMeeting")) {   				
    				Model_User_Account user = main.getHome().getMenu_Left().getUser(jsonData.getInt("userId"));
    				user.setStatus(true);
    				main.getHome_community().getMeeting_room().getMenuLeft().newUser(user);
	    	}
	    	else if(jsonData.getString("type").equals("leaveMeeting")) {   				
    			if(main.getHome_community().getMeeting_room().getMeetingId() == jsonData.getInt("meetingId")) {
    				main.getHome_community().getMeeting_room().getMenuLeft().userLeave(jsonData.getInt("userId"));    				
    			}
	    	}
	    	else if(jsonData.getString("type").equals("stopShare")) {   				
    			if(main.getHome_community().getMeeting_room().getMeetingId() == jsonData.getInt("meetingId")) {
    				main.getHome_community().getMeeting_room().getScreen().getPanel().getGraphics().clearRect(0, 0, main.getHome_community().getMeeting_room().getScreen().getPanel().getWidth(), main.getHome_community().getMeeting_room().getScreen().getPanel().getHeight());
    				main.getHome_community().getMeeting_room().getScreen().getPanel().setBackground(Color.black);   				
    			}
	    	}
	    	else if(jsonData.getString("type").equals("messageMeeting")) {   				
    			if(main.getHome_community().getMeeting_room().getMeetingId() == jsonData.getInt("meetingId")) {
    				Model_Message_Meeting message = new Model_Message_Meeting(jsonData);
    				main.getHome_community().getMeeting_room().getMenuLeft().getChat_meeting().getChat_body_meeting().addItemLeft(message);
    			}
	    	}
	    	

		} catch (JSONException e) {
			e.printStackTrace();
		}
    }
    
	public void listenImage(BufferedImage img) {
	        main.getHome_community().getMeeting_room().getScreen().getPanel().getGraphics().drawImage(img, 0, 0, main.getHome_community().getMeeting_room().getScreen().getPanel().getWidth(), main.getHome_community().getMeeting_room().getScreen().getPanel().getHeight(), null);
	}
	public static AudioFormat getaudioformat() {
		float sampleRate = 8000.0F;
		int sampleSizeInbits = 16;
		int channel = 2;
		boolean signed = true;
		boolean bigEndian = false;
		return new AudioFormat(sampleRate, sampleSizeInbits, channel, signed, signed);
	}
	
	
	public void listenMeeting(int userId, int meetingId, DatagramSocket out1, DatagramSocket out2) {				
		new Thread(() -> {
        	int UDP_PORT = userId;
        	int UDP_PORT_SERVER = meetingId;
        	TargetDataLine audio_in;
        	SourceDataLine audio_out;
        	DatagramSocket dout = out1;
        	try {
        		AudioFormat format = getaudioformat();
        		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        		if (!AudioSystem.isLineSupported(info)) {
        			System.out.println("Not support");
        		}
        		audio_in = (TargetDataLine) AudioSystem.getLine(info);
        		audio_in.open(format);
        		audio_in.start();
        		
        		InetAddress inet = InetAddress.getByName(SERVER_IP);        		
//        		dout = new DatagramSocket(UDP_PORT);
        		
        		byte byte_buff[] = new byte[512];
        		thread_mic = new Thread(()-> {
                    while (on_mic) {
                    	int i = 0;
                        try {
                            audio_in.read(byte_buff, 0, byte_buff.length);
                            DatagramPacket data = new DatagramPacket(byte_buff, byte_buff.length, inet, UDP_PORT_SERVER);
                            System.out.println(user.getUserName() + " mic");
                            dout.send(data);
                        } catch (IOException e) {                        	
                            e.printStackTrace();
                        }
                    }       			                    
        		});
//        		thread_mic.start();
//        		synchronized (thread_mic) {
//        			thread_mic.wait();
//				}
  
       			AudioFormat format2 = getaudioformat();
    			DataLine.Info info_out = new DataLine.Info(SourceDataLine.class, format2);
    			if (!AudioSystem.isLineSupported(info_out)) {
    				System.out.println("Not support");
    				System.exit(0);
    			}
    			audio_out = (SourceDataLine) AudioSystem.getLine(info_out);
    			audio_out.open(format);
    			audio_out.start();
        		byte[] buffer = new byte[512];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
                System.out.println("haha");
                
                thread_loa = new Thread(()->{
                    while (true) {
                        try {
                            dout.receive(incoming);
                            byte [] audio = incoming.getData();
//                            System.out.println(buffer);
                            // Phát dữ liệu âm thanh từ server ra loa
//                            audio_out.write(audio, 0, audio.length);
                            loa(audio_out, audio);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread_loa.start();
                
			} catch (Exception e2) {
				e2.printStackTrace();
			}
        }).start();
        
        new Thread(()->{
        	try {
            	int UDP_PORT = userId + 1000;
            	int UDP_PORT_SERVER = meetingId + 1000;
            	DatagramSocket dout = out2;
        		byte[] buffer = new byte[65507];
//        		byte[] buffer = new byte[1024 * 1024];
                DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
    			InetAddress inet = InetAddress.getByName(SERVER_IP);
//    			dout = new DatagramSocket(UDP_PORT);        		
                
		        thread_img = new Thread(() -> {
					while(on_img) {
						System.out.println(user.getUserName() + " img");
						try {
							Robot rob = new Robot();  
							Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
                            BufferedImage img = rob.createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
                            
//                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                            ImageIO.write(img, "jpg", baos);
//                            byte[] imageData = baos.toByteArray();
//                            System.out.println("imageSize : " + imageData.length);
//                            baos.close();
                            
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
                            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
                            writer.setOutput(ios);
                            ImageWriteParam param = writer.getDefaultWriteParam();
                            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                            param.setCompressionQuality(0.1f); // Chất lượng ảnh 10%
                            writer.write(null, new IIOImage(img, null, null), param);
                            writer.dispose();
                            ios.close();

                            byte[] imageData = baos.toByteArray();
                            baos.close();
                            
                            DatagramPacket packet = new DatagramPacket(imageData, imageData.length, inet, UDP_PORT_SERVER);
                            dout.send(packet);
			               
						} catch (Exception e1) {
							e1.printStackTrace();
						}
	                    try {
	                        Thread.sleep(100);
	                    } catch (Exception e1) {
	                    	e1.printStackTrace();
	                    }
					}
		        });
		        
//		        thread_img.start();
//		        synchronized (thread_img) {
//		        	thread_img.wait();
//				}
    			
    			while (true) {
                    try {
                        dout.receive(incoming);

                        byte[] imageData = incoming.getData();
                        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
                        BufferedImage image = ImageIO.read(bais);
                        
                        if (image != null) {
                            listenImage(image);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }).start();
	}
	
	public void loa(SourceDataLine audio_out, byte[] audio) {
		if(true) {
			System.out.println(user.getUserName() + " loa");
			audio_out.write(audio, 0, audio.length);
		}
	}
	
	public void share(BufferedImage img) {
        try {
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
			ImageIO.write(img, "png", ous);
			out.write(ous.toByteArray());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    public void sendLogin(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();
    }
	
	
    
    public void sendRegister(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void registerInfo(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void updateInfo(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void sendMessage(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void historyMessage(int user_Id2) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "historyMessage");
			json.put("user_Id2", user_Id2);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void sendFile(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start();  
    }
    
    public void addProject(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listProject() {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listProject");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listPost(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listPost");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listProg(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listProg");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }

    
    public void listMember(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listMember");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    
//    public void postNews(JSONObject jsonData) {
//        new Thread(() -> {
//            try {
//    			out.writeBytes(jsonData.toString() + "\n");
//    			out.flush();
//    		} catch (IOException e) {
//    			e.printStackTrace();
//    		}
//        }).start(); 
//    }
    
    public void postNews(JSONObject jsonData) {
        new Thread(() -> {
            try {
                // Sử dụng OutputStreamWriter để viết dữ liệu dưới dạng UTF-8
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start(); 
    }
    
    public void newProg(JSONObject jsonData) {
        new Thread(() -> {
            try {
                // Sử dụng OutputStreamWriter để viết dữ liệu dưới dạng UTF-8
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start(); 
    }
    
    public void addMember(String userName, int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "addMember");
			json.put("userName", userName);
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void addCalendar(JSONObject jsonData) {
    	try {
			jsonData.put("type", "addCalendar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void updateCalendar(JSONObject jsonData) {
    	try {
			jsonData.put("type", "updateCalendar");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void listCalendar() {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listCalendar");
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }    
    
    public void addMeeting(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public synchronized void listMeeting(int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "listMeeting");
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public synchronized void openMeeting(int meetingId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "openMeeting");
			json.put("meetingId", meetingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void joinMeeting(int meetingId, int userId, int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "joinMeeting");
			json.put("meetingId", meetingId);
			json.put("projectId", projectId);
			json.put("userId", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void leaveMeeting(int meetingId, int userId, int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "leaveMeeting");
			json.put("meetingId", meetingId);
			json.put("projectId", projectId);
			json.put("userId", userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void joinedMeeting(int userId, int newUserId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "joinedMeeting");
			json.put("userId", userId);
			json.put("newUserId", newUserId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void stopShare(int meetingId, int projectId) {
    	JSONObject json = new JSONObject();
		try {
			json.put("type", "stopShare");
			json.put("meetingId", meetingId);
			json.put("projectId", projectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(json.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    public void messageMeeting(JSONObject jsonData) {
        new Thread(() -> {
            try {
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }).start(); 
    }
    
    
    public void newMeetingRoom(int meetingId, int projectId, DatagramSocket dout, DatagramSocket dout2) {
    	main.getHome_community().newMeetingRoom(meetingId, projectId ,dout, dout2);
    }
	
    public Socket getClient() {
        return client;
    }
    
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

	public Model_Message getModel_message() {
		return model_message;
	}

	public Login getLogin() {
		return login;
	}

	public MainUI getMain() {
		return main;
	}

	public boolean isOn_mic() {
		return on_mic;
	}

	public void setOn_mic(boolean on_mic) {
		this.on_mic = on_mic;
		if(on_mic) {
			if(thread_mic != null && thread_mic.getState() != Thread.State.NEW) {
				synchronized (thread_mic) {
					thread_mic.notify();
				}
			}
			else {
				thread_mic.start();
			}

		}
		else {
			try {
				synchronized (thread_mic) {
					thread_mic.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isOn_loa() {
		return on_loa;
	}

	public void setOn_loa(boolean on_loa) {
		this.on_loa = on_loa;
//		if(on_loa) {
//			synchronized (thread_loa) {
//				thread_loa.notify();
//			}
//		}
//		else {
//			try {
//				synchronized (thread_loa) {
//					thread_loa.wait();
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public boolean isOn_img() {
		return on_img;
	}

	public void setOn_img(boolean on_img) {
		this.on_img = on_img;
		if(on_img) {
			if(thread_img != null && thread_img.getState() != Thread.State.NEW) {
				synchronized (thread_img) {
					thread_img.notify();
				}
			}
			else {
				thread_img.start();
			}
		}
		else {
			try {
				synchronized (thread_img) {
					thread_img.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Thread getThread_loa() {
		return thread_loa;
	}

	public Thread getThread_mic() {
		return thread_mic;
	}

	public Thread getThread_img() {
		return thread_img;
	}

}
