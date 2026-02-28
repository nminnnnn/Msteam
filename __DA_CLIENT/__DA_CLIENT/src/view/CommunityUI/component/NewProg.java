package view.CommunityUI.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.ImageDecoder;
import model.Chat.Model_User_Account;
import view.ChatUI.swing.ImageAvatar;

public class NewProg extends JPanel{
	private JTextArea textArea;
	private JButton bt_add;
	
	public NewProg() {
		JPanel panel_title = new JPanel();
		panel_title.setBackground(Color.white);
		
		JPanel panel_content = new JPanel();
		
		JLabel lb_userName = new JLabel("NEW PROGRESS");
		lb_userName.setFont(new Font("Tahoma", Font.BOLD, 24));
		GroupLayout gl_panel_title = new GroupLayout(panel_title);
		gl_panel_title.setHorizontalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addComponent(lb_userName, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
		);
		gl_panel_title.setVerticalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_title.createSequentialGroup()
					.addComponent(lb_userName, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
					.addGap(2))
		);
		panel_title.setLayout(gl_panel_title);
		
		bt_add = new JButton("ADD");
		bt_add.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(bt_add, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
						.addComponent(panel_content, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
						.addComponent(panel_title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_title, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_content, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_add, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
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

	public JButton getBt_add() {
		return bt_add;
	}
	
	

}
