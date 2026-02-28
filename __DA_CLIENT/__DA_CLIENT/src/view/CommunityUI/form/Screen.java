package view.CommunityUI.form;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.community.Model_Project;
import service.Service;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramSocket;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Screen extends JPanel{
	private int projectId;
	private JPanel panel;
	private CardLayout cardLayout_share;
	private CardLayout cardLayout_mic;
	private DatagramSocket dout;
	private DatagramSocket dout2;
	private Meeting_room meeting_room;
	
	public Screen(int projectId, DatagramSocket dout, DatagramSocket dout2, Meeting_room meeting_room) {
		this.projectId = projectId;
		this.dout = dout;
		this.dout2 = dout2;
		this.meeting_room = meeting_room;
		
		panel = new JPanel();
		panel.setBackground(Color.black);
		setBackground(SystemColor.scrollbar);

		JButton bt_leave = new JButton("");
		bt_leave.setIcon(new ImageIcon(Screen.class.getResource("/images/background/icon_leave.png")));
		bt_leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dout.close();
				dout2.close();
				meeting_room.dispose();
				Service.getInstance().leaveMeeting(meeting_room.getMeetingId(), Service.getInstance().getUser().getUser_Id(), meeting_room.getProject());
			}
		});
		
		JPanel panel_button_voice = new JPanel();
		
		JPanel panel_button_share = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(302)
					.addComponent(panel_button_share, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
					.addComponent(bt_leave, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(247)
					.addComponent(panel_button_voice, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(244))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_button_voice, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(bt_leave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
						.addComponent(panel_button_share, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		cardLayout_share = new CardLayout(0, 0);
		panel_button_share.setLayout(cardLayout_share);
		
		JButton bt_on_share = new JButton("");
		bt_on_share.setIcon(new ImageIcon(Screen.class.getResource("/images/background/icon_share.png")));
		bt_on_share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    cardLayout_share.next(panel_button_share);
                    Service.getInstance().setOn_img(true);            
                });			
			}
		});
		panel_button_share.add(bt_on_share, "bt_on_share");
		
		JButton bt_off_share = new JButton("");
		bt_off_share.setIcon(new ImageIcon(Screen.class.getResource("/images/background/icon_stop_share.png")));
		panel_button_share.add(bt_off_share, "bt_off_share");
		bt_off_share.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              SwingUtilities.invokeLater(() -> {
	                    cardLayout_share.next(panel_button_share);
	                    Service.getInstance().setOn_img(false);
	                    Service.getInstance().stopShare(meeting_room.getMeetingId(), meeting_room.getProject());
	                });
			}
		});
		
		cardLayout_mic = new CardLayout(0, 0);
		panel_button_voice.setLayout(cardLayout_mic);
		
		JButton bt_on_mic = new JButton("");
		bt_on_mic.setIcon(new ImageIcon(Screen.class.getResource("/images/background/icon_on_mic.png")));
		bt_on_mic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout_mic.next(panel_button_voice);
				Service.getInstance().setOn_mic(true);
				Service.getInstance().setOn_loa(false);
			}
		});
		panel_button_voice.add(bt_on_mic, "bt_on_mic");
		setLayout(groupLayout);
		
		JButton bt_off_mic= new JButton("");
		bt_off_mic.setIcon(new ImageIcon(Screen.class.getResource("/images/background/icon_off_mic.png")));
		bt_off_mic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout_mic.next(panel_button_voice);
				Service.getInstance().setOn_loa(true);
				Service.getInstance().setOn_mic(false);
			}
		});
		panel_button_voice.add(bt_off_mic, "bt_off_mic");
	}
	

	public JPanel getPanel() {
		return panel;
	}
}
