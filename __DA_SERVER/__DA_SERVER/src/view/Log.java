package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import model.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import service.ClientHandler;
import service.Service;
import service.ServiceUser;
import view.component.ItemLog;
import view.component.Item_People;

import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.SystemColor;

public class Log extends JPanel {
	private static Log instance;
	public static ArrayList<ItemLog> logs = new ArrayList<>();;

	private ServiceUser serviceUser;
	private JPanel list_user;

	private JPanel panel_user;

	private JPanel list_log;
	private JPanel panel_log;
	
	public static Log getInstance() {
		if(instance == null) {
			instance = new Log();
		}
		return instance;
	}

	public Log() {
		setBackground(Color.WHITE);
		list_user = new JPanel();
		list_user.setBackground(Color.WHITE);
		serviceUser = new ServiceUser(Service.getInstance().getClients());
		initComponents();
	}
	
	public void initComponents() {	
		
		
		setSize(1240, 840);
		setLayout(null);
		
		panel_user = new JPanel();
		panel_user.setBounds(10, 84, 300, 671);
		panel_user.setSize(300, 671);
		panel_user.setLayout(new MigLayout("fillx, filly", "[300]", "[100%,fill]"));
		add(panel_user);
		
		

		list_user.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
		JScrollPane jScrollPane = new JScrollPane(list_user);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_user.add(jScrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel("Active");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 46, 300, 36);
		add(lblNewLabel);
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLog.setBounds(330, 46, 889, 36);
		add(lblLog);
		
		panel_log = new JPanel();
		panel_log.setBounds(352, 84, 850, 671);
		add(panel_log);
		panel_log.setLayout(null);
		
		list_log = new JPanel();
		list_log.setBackground(SystemColor.menu);
		list_log.setLayout(new MigLayout("fillx", "5[810]5", "5[]5"));
		
		JScrollPane scrollPane2 = new JScrollPane(list_log);
		scrollPane2.setBounds(10, 10, 840, 651);
		panel_log.add(scrollPane2);
		
		loadUser();
		
		loadLog();
	}
	
	public void loadUser() {
		SwingUtilities.invokeLater(() -> { 
			List<Model_User_Account> list = serviceUser.getUser();
			for(ClientHandler client : Service.getInstance().getClients()) {
				for(Model_User_Account user : list) {		
					if(client.getUserId().equals(user.getUser_Id()+"")) {
						Item_People item = new Item_People(user);
						item.active(true);
						list_user.add(item, "width 280:280:280, height 50:50:50, wrap");
						System.out.println("Theem nugoi");
						break;
					}
				}
			}
			list_user.repaint();
			list_user.revalidate();
			panel_user.repaint();
			panel_user.revalidate();
		    this.revalidate();     
		    this.repaint(); 
		 });
	}
	
	public void loadLog() {
		list_log.removeAll();
		SwingUtilities.invokeLater(() -> { 
			for(ItemLog log : logs) {					
				list_log.add(log, "width 810:810:810, height 100:150:100, wrap");
			}
			list_log.repaint();
			list_log.revalidate();
			panel_log.repaint();
			panel_log.revalidate();
		    this.revalidate();     
		    this.repaint(); 
		 });
	}
	
	public void writeLog(ItemLog log) {
		SwingUtilities.invokeLater(() -> { 				
			list_log.add(log, "width 730:730:730, wrap");
			
			list_log.repaint();
			list_log.revalidate();
			panel_log.repaint();
			panel_log.revalidate();
		    this.revalidate();     
		    this.repaint(); 
		 });
	}
	
	
}
