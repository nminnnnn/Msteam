package view.CommunityUI.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Chat.Model_Send_Message;
import model.Chat.Model_User_Account;
import model.community.Model_Message_Meeting;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.JIMSendTextPane;
import view.CommunityUI.form.Meeting_room;

public class Chat_bottom_meeting extends JPanel{
    private Meeting_room meeting_room;
    
	public Chat_bottom_meeting(Meeting_room meeting_room) {
		this.meeting_room = meeting_room;
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
                    Model_Message_Meeting message = new Model_Message_Meeting(meeting_room.getMeetingId(), meeting_room.getProject(), Service.getInstance().getUser().getFullName(), text);
                    send(message);
                    Service.getInstance().getMain().getHome_community().getMeeting_room().getMenuLeft().getChat_meeting().getChat_body_meeting().addItemRight(message);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
            }
        });
        panel.add(cmd);
        add(panel);
	}
	
	private void send(Model_Message_Meeting data) {
		Service.getInstance().messageMeeting(data.toJsonObject("messageMeeting"));
    }
	
	public void refresh() {
		revalidate();
	}

}
