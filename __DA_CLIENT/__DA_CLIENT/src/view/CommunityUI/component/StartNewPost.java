package view.CommunityUI.component;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import view.ChatUI.swing.ImageAvatar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import model.ImageDecoder;
import model.Chat.Model_User_Account;

public class StartNewPost extends JPanel{
	private ImageAvatar imageAvatar;
	private JTextArea textArea;
	private JButton bt_dangbai;
	private Model_User_Account user;
	
	public StartNewPost(Model_User_Account user) {
		this.user = user;
		
		JPanel panel_title = new JPanel();
		panel_title.setBackground(Color.white);
		
		JPanel panel_content = new JPanel();
		
		imageAvatar = new ImageAvatar();
		imageAvatar.setBorderSize(0);
//		imageAvatar.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
		imageAvatar.setImage(ImageDecoder.decodeByteArrayToImageIcon(user.getAvatar()));
		
		JLabel lb_userName = new JLabel(user.getFullName());
		lb_userName.setFont(new Font("Tahoma", Font.BOLD, 22));
		GroupLayout gl_panel_title = new GroupLayout(panel_title);
		gl_panel_title.setHorizontalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_title.createSequentialGroup()
					.addComponent(imageAvatar, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lb_userName, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(505, Short.MAX_VALUE))
		);
		gl_panel_title.setVerticalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_title.createSequentialGroup()
					.addContainerGap()
					.addComponent(lb_userName, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(16, Short.MAX_VALUE))
				.addComponent(imageAvatar, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
		);
		panel_title.setLayout(gl_panel_title);
		
		bt_dangbai = new JButton("ĐĂNG BÀI");
		bt_dangbai.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(bt_dangbai, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
						.addComponent(panel_content, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
						.addComponent(panel_title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_title, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_content, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(bt_dangbai, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		GroupLayout gl_panel_content = new GroupLayout(panel_content);
		gl_panel_content.setHorizontalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 894, Short.MAX_VALUE)
		);
		gl_panel_content.setVerticalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
		);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane.setViewportView(textArea);
		panel_content.setLayout(gl_panel_content);
		setLayout(groupLayout);
		setBackground(Color.white);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JButton getBt_dangbai() {
		return bt_dangbai;
	}

	public Model_User_Account getUser() {
		return user;
	}

	public void setUser(Model_User_Account user) {
		this.user = user;
	}
	
	
}
