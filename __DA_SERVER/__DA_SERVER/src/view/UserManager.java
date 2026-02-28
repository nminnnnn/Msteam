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
import java.awt.Color;

public class UserManager extends JPanel {
	private JTable table;
	private DefaultTableModel table_model;
	private ServiceUser serviceUser;
	private JTextField textField;
	private int totalUsers;

	public UserManager() {
		setBackground(Color.WHITE);
		serviceUser = new ServiceUser(Service.getInstance().getClients());

		setSize(1240, 840);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(78, 176, 1100, 605);
		add(scrollPane);

		table_model = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "", "Username", "FullName", "Email", "Phone" });
		table = new JTable();
		table.setModel(table_model);
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(220);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(4).setPreferredWidth(300);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setCellRenderer(new ImageRenderer());
		table.setFont(new Font("Tahoma", Font.BOLD, 20));

		Font headerFont = new Font("Arial", Font.BOLD, 25);
		table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
		table.getTableHeader().setFont(headerFont);
		table.setRowHeight(60);

		scrollPane.setViewportView(table);
		
		loadUser();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(173, 216, 230));
		panel.setBounds(78, 29, 353, 124);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Number of users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 92, 353, 32);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(totalUsers + "");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 80));
		lblNewLabel_1.setBounds(0, 0, 353, 87);
		panel.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 18));
		textField.setBounds(723, 118, 455, 35);
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

		table_model.setRowCount(0);
		for (Model_User_Account user : list) {
			totalUsers++;
			
			ImageIcon image = ImageUtil.bytesToImageIcon(user.getAvatar(), 80, 80);

			Object[] newRow = { user.getUser_Id(), image, user.getUserName(), user.getFullName(), user.getEmail(),
					user.getPhone() };
			table_model.addRow(newRow);
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
