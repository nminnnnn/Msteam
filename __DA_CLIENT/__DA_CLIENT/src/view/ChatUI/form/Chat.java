package view.ChatUI.form;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import model.Chat.Model_File;
import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;
import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.ChatUI.component.Chat_Body;
import view.ChatUI.component.Chat_Bottom;
import view.ChatUI.component.Chat_Title;
import view.ChatUI.event.EventChat;
import view.ChatUI.event.PublicEvent;

public class Chat extends JPanel{
	private Chat_Body chatBody;
	private Chat_Bottom chatBottom;
	private Chat_Title chatTitle;
	
	public Chat() {
		chatBody = new Chat_Body();
		chatBottom = new Chat_Bottom();
		chatTitle = new Chat_Title();
		
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
		
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, fill]0[shrink 0]0"));
        
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(Model_Send_Message data) {
                chatBody.addItemRight(data);
            }
            
            @Override
            public void sendMessage(Model_File data) {
                chatBody.addItemRight(data);
            }

            @Override
            public void receiveMessage(Model_Receive_Message data) {
                if (chatTitle.getUser().getUser_Id() == data.getFromUserID()) {
                    chatBody.addItemLeft(data);
                }
            }
            
            @Override
            public void receiveMessage(Model_File data) {
                if (chatTitle.getUser().getUser_Id() == data.getFromUserID()) {
                    chatBody.addItemLeft(data);
                }
            }
        });
        
        add(chatTitle, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h :: 50%");
	}
	
    public void setUser(Model_User_Account user) {
        chatTitle.setUserName(user);
        chatBottom.setUser(user);
        chatBody.setUser(user);
        chatBody.clearChat();
        Service.getInstance().historyMessage(user.getUser_Id());
    }

    public void updateUser(Model_User_Account user) {
        chatTitle.updateUser(user);
    }

	public Chat_Body getChatBody() {
		return chatBody;
	}

	public Chat_Title getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(Chat_Title chatTitle) {
		this.chatTitle = chatTitle;
	}
    
	
    
}
