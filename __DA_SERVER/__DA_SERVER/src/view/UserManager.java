package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Model_User_Account;
import service.Service;
import service.ServiceUser;
import utils.ImageRenderer;
import utils.ImageUtil;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

public class UserManager extends JPanel {
	private JTable table;
	private DefaultTableModel table_model;
	private ServiceUser serviceUser;
	private JTextField textField;
	private int totalUsers;
	private JLabel lblUserCount;

	public UserManager() {
		setBackground(new Color(248, 250, 252));
		serviceUser = new ServiceUser(Service.getInstance().getClients());

		setSize(1240, 840);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 176, 1100, 605);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(scrollPane);

		table_model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "", "Username", "FullName", "Email", "Phone" });
		table = new JTable(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		table.setRowHeight(56);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 2));

		Font headerFont = new Font("Segoe UI", Font.BOLD, 18);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 44));
		table.getTableHeader().setFont(headerFont);
		table.getTableHeader().setBackground(new Color(54, 174, 255));
		table.getTableHeader().setForeground(Color.WHITE);

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(54, 174, 255));
		panel.setBounds(78, 29, 353, 124);
		panel.setLayout(null);
		add(panel);

		JLabel lblNewLabel = new JLabel("Số lượng user");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 88, 353, 28);
		panel.add(lblNewLabel);

		lblUserCount = new JLabel("0");
		lblUserCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserCount.setFont(new Font("Segoe UI", Font.BOLD, 56));
		lblUserCount.setForeground(Color.WHITE);
		lblUserCount.setBounds(0, 8, 353, 80);
		panel.add(lblUserCount);
		
		loadUser();

		JLabel lblSearch = new JLabel("Tìm user (username / tên):");
		lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblSearch.setBounds(478, 80, 280, 28);
		add(lblSearch);
		textField = new JTextField();
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		textField.setBounds(478, 118, 455, 38);
		textField.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(200, 212, 225), 1),
			BorderFactory.createEmptyBorder(8, 12, 8, 12)));
		add(textField);
		textField.setColumns(10);
		
		textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
            	search();
            }
            public void removeUpdate(DocumentEvent e) {
            	search();
            }
            public void changedUpdate(DocumentEvent e) {
            	search();
            }
        });

		
	}

	public void loadUser() {
		List<Model_User_Account> list = serviceUser.getUser();
		totalUsers = 0;
		table_model.setRowCount(0);
		for (Model_User_Account user : list) {
			totalUsers++;
			ImageIcon image = ImageUtil.bytesToImageIcon(user.getAvatar(), 80, 80);
			Object[] newRow = { user.getUser_Id(), image, user.getUserName(), user.getFullName(), user.getEmail(),
					user.getPhone() };
			table_model.addRow(newRow);
		}
		if (lblUserCount != null) {
			lblUserCount.setText(String.valueOf(totalUsers));
		}
	}

	public void search() {
		String name = textField.getText();
		List<Model_User_Account> list = new ArrayList<>();
		if(name.isEmpty()) {
			list = serviceUser.getUser();
		} else {
			list = serviceUser.search("%" + name + "%");
		}
		 

		table_model.setRowCount(0);
		for (Model_User_Account user : list) {
			ImageIcon image = ImageUtil.bytesToImageIcon(user.getAvatar(), 80, 80);

			Object[] newRow = { user.getUser_Id(), image, user.getUserName(), user.getFullName(), user.getEmail(),
					user.getPhone() };
			table_model.addRow(newRow);
		}
	}
}
