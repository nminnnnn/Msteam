package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import service.Service;

public class MenuLeft extends JPanel{
	
	private JLabel lb_dashboard;
	private JLabel lb_user;
	private JLabel lb_project;
	private JLabel lb_log;

	public MenuLeft() {
		setSize(300, 840);
		setLayout(null);
		setBackground(new Color(54, 174, 255));
		
		lb_dashboard = new JLabel("DASHBOARD");
//		lb_dashboard.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/cuahang.png")));
		lb_dashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_dashboard.setBackground(new Color(5, 150, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_dashboard.setBackground(new Color(95, 187, 250));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
//				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "dashboard");
				Service.getInstance().getMain().getBody().showPage("dashboard");
				lb_dashboard.setBackground(new Color(0, 104, 176));
			}
		});
		lb_dashboard.setForeground(new Color(255, 255, 255));
		lb_dashboard.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_dashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dashboard.setBounds(0, 283, 300, 58);
		lb_dashboard.setBackground(new Color(95, 187, 250));
		lb_dashboard.setOpaque(true);
		add(lb_dashboard);
		
		lb_user = new JLabel("USER");
//		lb_user.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/nhanvien.png")));
		lb_user.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_user.setBackground(new Color(5, 150, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_user.setBackground(new Color(95, 187, 250));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
//				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "userManager");
				Service.getInstance().getMain().getBody().showPage("userManager");
				lb_user.setBackground(new Color(0, 104, 176));
			}
		});
		lb_user.setOpaque(true);
		lb_user.setHorizontalAlignment(SwingConstants.CENTER);
		lb_user.setForeground(Color.WHITE);
		lb_user.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_user.setBackground(new Color(95, 187, 250));
		lb_user.setBounds(0, 345, 300, 58);
		add(lb_user);
		
		lb_project = new JLabel("PROJECT");
//		lb_project.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/douong.png")));
		lb_project.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_project.setBackground(new Color(5, 150, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_project.setBackground(new Color(95, 187, 250));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
//				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "projectManager");
				Service.getInstance().getMain().getBody().showPage("projectManager");
				lb_project.setBackground(new Color(0, 104, 176));
			}
		});
		lb_project.setOpaque(true);
		lb_project.setHorizontalAlignment(SwingConstants.CENTER);
		lb_project.setForeground(Color.WHITE);
		lb_project.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_project.setBackground(new Color(95, 187, 250));
		lb_project.setBounds(0, 407, 300, 58);
		add(lb_project);
		
		lb_log = new JLabel("LOG");
//		lb_log.setIcon(new ImageIcon(MenuLeft.class.getResource("/images/kahchhang.png")));
		lb_log.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lb_log.setBackground(new Color(5, 150, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lb_log.setBackground(new Color(95, 187, 250));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				reset();
//				Service.getInstance().getMain().getBody().getCardLayout().show(Service.getInstance().getMain().getBody(), "log");
				Service.getInstance().getMain().getBody().showPage("log");
				lb_log.setBackground(new Color(0, 104, 176));
			}
		});
		lb_log.setOpaque(true);
		lb_log.setHorizontalAlignment(SwingConstants.CENTER);
		lb_log.setForeground(Color.WHITE);
		lb_log.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_log.setBackground(new Color(95, 187, 250));
		lb_log.setBounds(0, 470, 300, 58);
		add(lb_log);
		
	}
	
	public void reset() {
		Service.getInstance().getMain().getBody().refresh();
	}
}
