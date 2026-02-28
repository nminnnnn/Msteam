package service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;

import org.json.JSONException;
import org.json.JSONObject;

import view.Body;
import view.component.ItemLog;


public class ClientHandler extends Thread{
    private BufferedReader in;
    private DataOutputStream out;
    private InputStream in_image;
    private OutputStream out_image;
    private ArrayList<ClientHandler> clients;
    private Service service;
    private String userId;
    private Socket socket;
      
    

	public ClientHandler(String userId, Service service, BufferedReader in, DataOutputStream out,InputStream in_image,OutputStream out_image,  ArrayList<ClientHandler> clients, Socket socket) {
        this.userId = userId;
    	this.service = service;
    	this.in = in;
        this.out = out;
        this.in_image = in_image;
        this.out_image = out_image;
        this.clients = clients;
        clients.add(this);
        this.socket = socket;
        start();
    }
    
    public void sendMessage(JSONObject jsonData) {
            try {
//    			out.writeBytes(jsonData.toString() + "\n");
                OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                writer.write(jsonData.toString() + "\n");
                writer.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    }
	
    
    public void sendImage(BufferedImage img){
        try {
            ByteArrayOutputStream ous = new ByteArrayOutputStream();
			if(ImageIO.write(img, "png", ous)) {
				out_image.write(ous.toByteArray());
				out_image.flush();
			}else {
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
    }
    
    private String decryptAES(String encryptedMessage, SecretKey secretKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher decryptCipher = Cipher.getInstance("AES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

	@Override
	public void run() {
		new Thread(() -> {
	        while (true) {
	            try {
	                String message ;
	            	synchronized (in) {
	            	    message = in.readLine();
	            	}
	                if (message != null) { // Client disconnected
//		                System.out.println("server: " + message);
		                service.listen(this, message);
//		                service.textArea.append("UTF_8 :" + message);
//		                broadcast(message);
	                }
	            	
	            } catch (Exception e) {
	                e.printStackTrace();
    	            try {
    	            	JSONObject jsonActive = new JSONObject();
						jsonActive.put("type", "noActive");
	    	            jsonActive.put("userId", Integer.parseInt(userId));
	    	            service.broadcastActive(userId, jsonActive);
	    	            
	    
	    	            for (ClientHandler client : clients) {
	    	                if (client.getUserId().equals(userId)) {
	    	                	clients.remove(client);
	    	                	break;
	    	                }
	    	            }
	    	            Service.getInstance().getMain().getBody().getLog().logs.add(new ItemLog("Client","One client disconnect!"));
	    	            Service.getInstance().getMain().getBody().refresh();
	    	            Service.getInstance().getMain().getBody().showPage(Body.currentPage);
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
	                System.out.println("đóng clientHandler");
	                break;
	            }
	        }
		}).start();
		
//		new Thread(() -> {
//	        while (true) {
//	            try {
////	            	BufferedImage img = ImageIO.read(in_image);
//                    BufferedImage img;
//                    synchronized (in_image) {
//                        img = ImageIO.read(in_image);
//                    }
//                    if(img != null) {
//                    	service.listenImage(img);
//                    }	            	
////	                server.getTextArea().append("receive2: " + img.toString() + "\n");
//	            } catch (Exception e) {
//	                e.printStackTrace();
////	                break;
//	            }
//	        }
//		}).start();		
		
		
		
	}
    
//    @Override
//    public void run() {
//    	boolean checkImg = true;
//    	boolean checkjson = true;
//        while (true) {
//        	if(checkjson) {
//                try {
//                    String message ;
//                	synchronized (in) {
//                	    message = in.readLine();
//                	}
//                    if(message != null) {
//                    	service.listen(this, message);
//                    	checkImg = false;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("đóng clientHandler");
////                    break;
//                }
//        	}
//            if(checkImg) {
//                try {
//                    BufferedImage img = null;
//                    synchronized (in_image) {
//                        img = ImageIO.read(in_image);
//                    }
//	                 if(img != null) {
//	              	   service.listenImage(img);	
//	              	   checkjson = false;
//	                 }
//                  } catch (Exception e) {
//                      e.printStackTrace();
////                      break;
//                  }
//            }
//            checkImg = true;
//            
//        }
//    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Socket getSocket() {
		return socket;
	}
	
	
}
