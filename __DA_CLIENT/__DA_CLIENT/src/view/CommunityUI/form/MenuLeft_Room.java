package view.CommunityUI.form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.Chat.Model_User_Account;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import view.MainUI;
import view.ChatUI.component.Item_People;
import view.ChatUI.event.EventMenuLeft;
import view.ChatUI.event.PublicEvent;
import view.CommunityUI.component.Chat_body_meeting;

import javax.swing.SwingConstants;

public class MenuLeft_Room extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User_Account> userAccount;
	private int projectId;
	private JPanel panel_menu;
	private CardLayout cardLayout;
	private Chat_meeting chat_meeting;
	private Meeting_room meeting_room;
	private JPanel cardPanel;

	public MenuLeft_Room(int projectId, Meeting_room meeting_room) {
		this.meeting_room = meeting_room;
		this.projectId = projectId;
		
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[50]0[100%,fill]0"));
		
		panel_menu = new JPanel();
		panel_menu.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton bt_chat2P = new JButton("");
		bt_chat2P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "member");
			}
		});
		panel_menu.add(bt_chat2P);
		
		JButton bt_chatGroup = new JButton("");
		bt_chatGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "chat");
				cardLayout.show(cardPanel, "member");
				cardLayout.show(cardPanel, "chat");
				cardLayout.show(cardPanel, "member");
				cardLayout.show(cardPanel, "chat");
				cardLayout.show(cardPanel, "member");
				cardLayout.show(cardPanel, "chat");
				cardLayout.show(cardPanel, "member");
				cardLayout.show(cardPanel, "chat");
			}
		});
		panel_menu.add(bt_chatGroup);
		
		bt_chat2P.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/chat2p.png"))).getImage())));
		bt_chatGroup.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/chatgroup.png"))).getImage())));
		
		add(panel_menu, "width 300:300:300, height 50:50:50, wrap");
		
		cardPanel = new JPanel();
		cardLayout = new CardLayout();
		cardPanel.setLayout(cardLayout);
		
		
		
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
//		JLabel label = new JLabel("NGƯỜI THAM GIA");
//		label.setOpaque(true);
//		label.setBackground(new Color(0, 191, 255));
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		label.setFont(new Font("Tahoma", Font.BOLD, 20));
//		add(label, "width 300:300:300, height 35:35:35, wrap");
		
		userAccount = new ArrayList<>();
//        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
//            @Override
//            public void newUser(Model_User_Account d) {	
//                    userAccount.add(d);
//                    panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
//                    panel_menu_list.repaint();
//                    panel_menu_list.revalidate();
//            }
//        });
		
		chat_meeting = new Chat_meeting(meeting_room);
		cardPanel.add(chat_meeting, "chat");
		
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		cardPanel.add(jScrollPane, "member");		
//		showPeople();
		setBackground(Color.blue);
		panel_menu_list.removeAll();
		
		cardLayout.show(cardPanel, "member");

				
		add(cardPanel);
	}
	
	 public void newUser(Model_User_Account d) {	
		  userAccount.add(d);
		  panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
		  panel_menu_list.repaint();
		  panel_menu_list.revalidate();
	}
	 
	public void userLeave(int userId) {
		Component[] components = panel_menu_list.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_People) {
		        Item_People item = (Item_People) component;
		        if(item.getUser().getUser_Id() == userId) {
		        	panel_menu_list.remove(item);
			  		panel_menu_list.repaint();
					panel_menu_list.revalidate();
		        	break;
		        }
		    }
		}
	}
	
	public void showPeople() {
		panel_menu_list.removeAll();
		
        for (Model_User_Account d : userAccount) {
        	panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
        }

		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}

	
	public boolean searchUser(String userName) {
		for(Model_User_Account user : userAccount) {
			if(user.getUserName().equalsIgnoreCase(userName)) {
				return true;
			}
		}
		return false;
	}

	public JLayeredPane getPanel_menu_list() {
		return panel_menu_list;
	}

	public void setPanel_menu_list(JLayeredPane panel_menu_list) {
		this.panel_menu_list = panel_menu_list;
	}

	public Chat_meeting getChat_meeting() {
		return chat_meeting;
	}

	public Meeting_room getMeeting_room() {
		return meeting_room;
	}

	
	
	
	
	
}
