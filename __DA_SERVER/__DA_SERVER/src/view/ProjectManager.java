package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import model.Model_Project_Manager;
import model.Model_User_Account;
import service.Service;
import service.ServiceCommunity;
import service.ServiceUser;
import utils.ImageRenderer;
import utils.ImageUtil;
import java.awt.Color;

public class ProjectManager extends JPanel {
	private JTable table;
	private DefaultTableModel table_model;
	private ServiceCommunity serviceCommunity;
	private JTextField textField;
	private int totalUsers;
	private int totalMeetings;
	private int totalPosts;

	public ProjectManager() {
		setBackground(Color.WHITE);
		serviceCommunity = new ServiceCommunity(0);

		setSize(1240, 840);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 176, 1100, 605);
		add(scrollPane);

		table_model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Total users", "Total meetings", "Total posts" });
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(430);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.setFont(new Font("Tahoma", Font.BOLD, 20));

		Font headerFont = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(50);

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 250, 154));
		panel.setBounds(78, 29, 353, 124);
		add(panel);
		panel.setLayout(null);
		
		loadProject();

		JLabel lblNewLabel = new JLabel("Total users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 92, 353, 32);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(totalUsers + "");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 80));
		lblNewLabel_1.setBounds(0, 0, 353, 87);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(238, 232, 170));
		panel_1.setLayout(null);
		panel_1.setBounds(451, 29, 353, 124);
		add(panel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total meetings");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(0, 92, 353, 32);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel(totalMeetings + "");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 80));
		lblNewLabel_1_1.setBounds(0, 0, 353, 87);
		panel_1.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(135, 206, 250));
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(825, 29, 353, 124);
		add(panel_1_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Total posts");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2_1.setBounds(0, 92, 353, 32);
		panel_1_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel(totalPosts + "");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 80));
		lblNewLabel_1_1_1.setBounds(0, 0, 353, 87);
		panel_1_1.add(lblNewLabel_1_1_1);

	}

	public void loadProject() {
		List<Model_Project_Manager> list = serviceCommunity.getProjectManager();

		table_model.setRowCount(0);
		for (Model_Project_Manager project : list) {
			totalUsers += project.getTotalUsers();
			totalMeetings += project.getTotalMeetings();
			totalPosts += project.getTotalPosts();
			
			Object[] newRow = {project.getProjectId(), project.getProjectName(), project.getTotalUsers(), project.getTotalMeetings(), project.getTotalPosts()  };
			table_model.addRow(newRow);
		}
	}

}

