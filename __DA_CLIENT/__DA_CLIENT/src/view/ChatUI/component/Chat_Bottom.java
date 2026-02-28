package view.ChatUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import model.Chat.Model_File;
import model.Chat.Model_Register;
import model.Chat.Model_Send_Message;
import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.JIMSendTextPane;

public class Chat_Bottom extends JPanel{

    private Model_User_Account user;
    
	public Chat_Bottom() {
        setBackground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        
        setLayout(new MigLayout("fillx, filly", "2[fill]0[]0[]2", "10[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setHintText("Write message here...");
        scroll.setViewportView(txt);
        add(scroll, "w 100%");
        
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.white);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/images/icon/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String text = txt.getText().trim();
                if (!text.equals("")) {
                    Date currentTime = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM");
                    String formattedTime = dateFormat.format(currentTime);
                	
                    Model_Send_Message message = new Model_Send_Message(Service.getInstance().getUser().getUser_Id(), user.getUser_Id(), text, formattedTime);
                    send(message);
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
            }
        });
        panel.add(cmd);
        
        JButton btnChooseFile = new JButton();
        btnChooseFile.setBorder(null);
        btnChooseFile.setContentAreaFilled(false);
        btnChooseFile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnChooseFile.setIcon(new ImageIcon(getClass().getResource("/images/icon/icon_file.png"))); 
        btnChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fileChooser = new JFileChooser();
                int option = fileChooser.showOpenDialog(Chat_Bottom.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        byte[] fileContent = readFileToByteArray(selectedFile);
                        Model_File modelFile = new Model_File(Service.getInstance().getUser().getUser_Id(),  user.getUser_Id(), selectedFile.getName(), fileContent);
                        
                        sendFile(modelFile);
                        PublicEvent.getInstance().getEventChat().sendMessage(modelFile);
                        System.out.println(modelFile.toJsonObject("file"));
                        
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        panel.add(btnChooseFile);
        
        add(panel);
	}
	
	private void send(Model_Send_Message data) {
		Service.getInstance().sendMessage(data.toJsonObject());
//        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }
	
	private void sendFile(Model_File data) {
		Service.getInstance().sendFile(data.toJsonObject("sendFile"));
    }
	
	public void refresh() {
		revalidate();
	}
	
    // Đọc nội dung file thành mảng byte
    private byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fileInputStream = null;
        byte[] fileContent = new byte[(int) file.length()];
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(fileContent);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
        return fileContent;
    }
	
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

}
