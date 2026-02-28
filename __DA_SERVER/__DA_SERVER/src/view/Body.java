package view;

import java.awt.CardLayout;

import javax.swing.JPanel;
import java.awt.Color;

public class Body extends JPanel{
	public static String currentPage;
	
	private CardLayout cardLayout;
	private DashBoard dashboard;
	private UserManager userManager;
	private ProjectManager projectManager;
	private Log log;

	public Body() {
		setSize(1240, 840);
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		currentPage = "dashboard";
		
		dashboard = new DashBoard();
		dashboard.setBackground(Color.WHITE);
		add(dashboard, "dashboard");	
		
		userManager = new UserManager();
		userManager.setBackground(Color.WHITE);
		add(userManager, "userManager");
		
		projectManager = new ProjectManager();
		projectManager.setBackground(Color.WHITE);
		add(projectManager, "projectManager");
		
		log = new Log();
		log.setBackground(Color.WHITE);
		add(log, "log");
		
	}
	
	public void refresh() {
		this.removeAll();
		
		cardLayout = new CardLayout(0, 0);
		this.setLayout(cardLayout);
		
		dashboard = new DashBoard();
		add(dashboard, "dashboard");	
		
		userManager = new UserManager();
		add(userManager, "userManager");
		
		projectManager = new ProjectManager();
		add(projectManager, "projectManager");
		
		log = new Log();
		add(log, "log");
	}
	
	public void showPage(String page) {
		currentPage = page;
		cardLayout.show(this, page);
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public DashBoard getDashboard() {
		return dashboard;
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public Log getLog() {
		return log;
	}
	
	
}
