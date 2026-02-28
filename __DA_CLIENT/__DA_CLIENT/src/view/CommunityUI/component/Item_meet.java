package view.CommunityUI.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.Chat.Model_User_Account;
import model.community.Model_Meeting;
import service.Service;
import view.ChatUI.swing.ImageAvatar;
import view.ChatUI.swing.JIMSendTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.awt.event.ActionEvent;

public class Item_meet extends JPanel{
	private Model_Meeting meeting;

	public Item_meet(Model_Meeting meeting) {
		this.meeting = meeting;
		
		setBorder(new EmptyBorder(0, 20, 10, 10));
		
		JPanel panel_content = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_content, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_content, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addGap(0))
		);
		
		JLabel lb_nameMeet = new JLabel();
		lb_nameMeet.setForeground(new Color(123, 104, 238));
		lb_nameMeet.setText(meeting.getTitle());
		lb_nameMeet.setPreferredSize(new Dimension(lb_nameMeet.getPreferredSize().width, lb_nameMeet.getPreferredSize().height));
		lb_nameMeet.setFont(new Font("Tahoma", Font.BOLD, 26));
		JLabel lb_date = new JLabel();
		lb_date.setText(meeting.getTime());
		lb_date.setPreferredSize(new Dimension(lb_date.getPreferredSize().width, lb_date.getPreferredSize().height));
		lb_date.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton bt_join = new JButton("Join");
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatagramSocket dout = new DatagramSocket(Service.getInstance().getUser().getUser_Id());
					DatagramSocket dout2 = new DatagramSocket(Service.getInstance().getUser().getUser_Id() + 1000); 
					Service.getInstance().openMeeting(meeting.getMeetingId());
					Service.getInstance().newMeetingRoom(meeting.getMeetingId(), meeting.getProjectId(), dout, dout2);	
    				Service.getInstance().getMain().getHome_community().getMeeting_room().getMenuLeft().newUser(Service.getInstance().getUser());
					Service.getInstance().joinMeeting(meeting.getMeetingId(), Service.getInstance().getUser().getUser_Id(), meeting.getProjectId());					
					Service.getInstance().listenMeeting(Service.getInstance().getUser().getUser_Id(), meeting.getMeetingId(), dout, dout2);
				} catch (SocketException e1) {
					e1.printStackTrace();
				}

			}
		});
		bt_join.setForeground(new Color(123, 104, 238));
		bt_join.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lb_idMeeting = new JLabel("ID: " + meeting.getMeetingId());
		lb_idMeeting.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel_content = new GroupLayout(panel_content);
		gl_panel_content.setHorizontalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_content.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_content.createParallelGroup(Alignment.TRAILING)
						.addComponent(lb_nameMeet, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
						.addComponent(lb_date, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_join, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addGap(26))
				.addGroup(gl_panel_content.createSequentialGroup()
					.addGap(10)
					.addComponent(lb_idMeeting, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_content.setVerticalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_content.createSequentialGroup()
					.addGroup(gl_panel_content.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_content.createSequentialGroup()
							.addGap(10)
							.addComponent(bt_join, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_panel_content.createSequentialGroup()
							.addComponent(lb_nameMeet, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lb_date, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lb_idMeeting, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_content.setLayout(gl_panel_content);
		setLayout(groupLayout);
		
		setBackground(Color.white);
		panel_content.setBackground(Color.white);
	}

	public Model_Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Model_Meeting meeting) {
		this.meeting = meeting;
	}
	
	
}
