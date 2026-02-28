package view.CommunityUI.form;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Community.Controller_MenuLeft;
import model.Chat.Model_User_Account;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.MainUI;
import view.ChatUI.component.Item_People;
import view.CommunityUI.component.Item_Project;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Menu_Left extends JPanel{
	
	private JPanel panel_menu_list;
	private JButton bt_add;
	private JButton bt_notification;
	private List<Model_Project> projects;

	public Menu_Left() {
		Controller_MenuLeft action_menuLeft = new Controller_MenuLeft(this);
		
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[50]3[100%, fill]0"));
		JPanel panel_title = new JPanel();
		add(panel_title, "width 300:300:300, wrap, height :: 50");
		panel_title.setLayout(new GridLayout(1, 2));
		JPanel panel_left = new JPanel();
		JPanel panel_right = new JPanel();
		panel_title.add(panel_left);
		panel_title.add(panel_right);
		panel_title.setBackground(new Color(150, 220, 248));
		
		panel_left.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel_left.setBorder(new EmptyBorder(7, 0, 0, 0));
		panel_left.setBackground(new Color(150, 220, 248));
		
		JLabel lb_title = new JLabel("COMMUNITY");
		lb_title.setFont(new Font("tohoma", Font.BOLD, 22));
		panel_left.add(lb_title);
		
		panel_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_right.setBackground(new Color(150, 220, 248));
		panel_right.setBorder(new EmptyBorder(0, 0, 20, 0));
		
		bt_add = new JButton("");
		bt_add.setPreferredSize(new Dimension(40, 40));
		bt_add.addActionListener(action_menuLeft);
		bt_add.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/icon_add.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
		panel_right.add(bt_add);
		
		bt_notification = new JButton("");
		bt_notification.addActionListener(action_menuLeft);
		bt_notification.setPreferredSize(new Dimension(40, 40));
		bt_notification.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/notification.png"))).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
		panel_right.add(bt_notification);
		
		panel_menu_list = new JPanel();
		panel_menu_list.setLayout(new MigLayout("fillx", "20[250!]30", "10[]10"));
		panel_menu_list.setBackground(new Color(202, 238, 251));
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		
//		showProject();		
	}
	
	public void showProject() {
		panel_menu_list.removeAll();
//        for (Model_Project p : projects) {
//    		panel_menu_list.add(new Item_Project(p) , "width 250:250:250, height 50:50:50, wrap");
//        }
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}
	
	public void addProject(Model_Project project) {
		panel_menu_list.add(new Item_Project(project) , "width 250:250:250, height 50:50:50, wrap");
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}

	public JButton getBt_add() {
		return bt_add;
	}

	public JButton getBt_notification() {
		return bt_notification;
	}

	public List<Model_Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Model_Project> projects) {
		this.projects = projects;
	}

	public JPanel getPanel_menu_list() {
		return panel_menu_list;
	}

	public void setPanel_menu_list(JPanel panel_menu_list) {
		this.panel_menu_list = panel_menu_list;
	}
	
	
}
