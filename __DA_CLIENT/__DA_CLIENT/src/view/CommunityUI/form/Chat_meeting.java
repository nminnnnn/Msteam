package view.CommunityUI.form;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;
import net.miginfocom.swing.MigLayout;
import view.ChatUI.component.Chat_Body;
import view.ChatUI.component.Chat_Bottom;
import view.ChatUI.component.Chat_Title;
import view.ChatUI.event.EventChat;
import view.ChatUI.event.PublicEvent;
import view.CommunityUI.component.Chat_body_meeting;
import view.CommunityUI.component.Chat_bottom_meeting;

public class Chat_meeting extends JPanel{
	private Chat_body_meeting chat_body_meeting;
	private Chat_bottom_meeting chat_bottom_meeting;
	private Meeting_room meeting_room;
	
	public Chat_meeting(Meeting_room meeting_room) {
		this.meeting_room = meeting_room;
		chat_body_meeting = new Chat_body_meeting();
		chat_bottom_meeting = new Chat_bottom_meeting(meeting_room);
		
        setBackground(new Color(255, 255, 255));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
		
        setLayout(new MigLayout("fillx, filly", "0[fill]0", "0[100%, fill]0[shrink 0]0"));
        
        add(chat_body_meeting, "wrap");
        add(chat_bottom_meeting, "h :: 50%, height 50:50:50");
	}

	public Chat_body_meeting getChat_body_meeting() {
		return chat_body_meeting;
	}

	public Chat_bottom_meeting getChat_bottom_meeting() {
		return chat_bottom_meeting;
	}
	
	

}
