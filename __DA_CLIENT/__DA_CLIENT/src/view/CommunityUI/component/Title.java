package view.CommunityUI.component;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;

import controller.CardLayout.CardLayout_Community;
import model.community.Model_Project;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Title extends JPanel{
	private JButton bt_post;
	private JButton bt_event;
	
	private Model_Project project;
	private JLabel lb_nameProject;
	private JButton bt_newMeeting;
	private JButton bt_member;
	private JButton bt_progress;

	public Title(Model_Project project) {
		this.project = project;
		
		lb_nameProject = new JLabel();
		lb_nameProject.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		bt_post = new JButton("Post");
		bt_post.setBorder(null);
		bt_post.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_post.setBackground(new Color(242, 242, 242));
		
		bt_event = new JButton("Event");
		bt_event.setBorder(null);
		bt_event.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_event.setBackground(new Color(242, 242, 242));
		
		bt_newMeeting = new JButton("New meeting");
		bt_newMeeting.setFont(new Font("Tahoma", Font.BOLD, 15));
		bt_newMeeting.setBackground(new Color(242, 242, 242));
		
		bt_member = new JButton("Member");
		bt_member.setFont(new Font("Tahoma", Font.BOLD, 15));
		bt_member.setBackground(new Color(242, 242, 242));
		
		bt_progress = new JButton("Progres");
		bt_progress.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_progress.setBorder(null);
		bt_progress.setBackground(new Color(242, 242, 242));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lb_nameProject, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(bt_post, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_event, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_progress, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
					.addComponent(bt_newMeeting)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_member)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bt_member, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(bt_newMeeting, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(lb_nameProject, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
				.addComponent(bt_event, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
				.addComponent(bt_post, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
				.addComponent(bt_progress, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		
		setBackground(Color.white);
	}
	
	

	public JButton getBt_newMeeting() {
		return bt_newMeeting;
	}

	public JButton getBt_member() {
		return bt_member;
	}

	public JButton getBt_post() {
		return bt_post;
	}

	public JButton getBt_event() {
		return bt_event;
	}
	
	public JButton getBt_progress() {
		return bt_progress;
	}

	public JLabel getLb_nameProject() {
		return lb_nameProject;
	}

	public void setLb_nameProject(JLabel lb_nameProject) {
		this.lb_nameProject = lb_nameProject;
	}

	public Model_Project getProject() {
		return project;
	}

	public void setProject(Model_Project project) {
		this.project = project;
		lb_nameProject.setText(project.getProjectName());
	}
}
