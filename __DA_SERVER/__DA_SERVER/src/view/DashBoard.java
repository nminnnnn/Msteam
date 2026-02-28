package view;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Model_Project_Manager;
import model.Model_User_Account;
import service.Service;
import service.ServiceCommunity;
import service.ServiceUser;
import utils.ImageUtil;
import view.component.chart.Chart;
import view.component.chart.ModelChart;
import view.component.pie.ModelPieChart;
import view.component.pie.PieChart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

public class DashBoard extends JPanel {
	private ServiceCommunity serviceCommunity;
	private ServiceUser serviceUser;
	
	private int totalUsers;
	private int totalMeetings;
	private int totalPosts;
	private int totalProjects;
	
	private Chart chart;
	private JPanel panel_chart;
	private JPanel panel_donut;
	private int[] chartList;
	private String[] month = {"", "January", "February","March","April","May","June","July","August","September","October","November","December"};
	
	private PieChart pieChart;
	
	public DashBoard() {
		setBackground(Color.WHITE);
		serviceCommunity = new ServiceCommunity(0);
		serviceUser = new ServiceUser(Service.getInstance().getClients());
		load();
		
		setSize(1240, 840);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 250, 154));
		panel.setBounds(32, 36, 276, 118);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Total Users");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 84, 276, 34);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(totalUsers + "");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_1.setBounds(0, 0, 276, 81);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(238, 232, 170));
		panel_1.setLayout(null);
		panel_1.setBounds(337, 36, 276, 118);
		add(panel_1);
		
		JLabel lblTotalProjects = new JLabel("Total Projects");
		lblTotalProjects.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalProjects.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotalProjects.setBounds(0, 84, 276, 34);
		panel_1.add(lblTotalProjects);
		
		JLabel lblNewLabel_1_1 = new JLabel(totalProjects + "");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_1_1.setBounds(0, 0, 276, 81);
		panel_1.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(135, 206, 235));
		panel_2.setLayout(null);
		panel_2.setBounds(639, 36, 276, 118);
		add(panel_2);
		
		JLabel lblTotalMeeting = new JLabel("Total Meeting");
		lblTotalMeeting.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalMeeting.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotalMeeting.setBounds(0, 84, 276, 34);
		panel_2.add(lblTotalMeeting);
		
		JLabel lblNewLabel_1_2 = new JLabel(totalMeetings + "");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_1_2.setBounds(0, 0, 276, 81);
		panel_2.add(lblNewLabel_1_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 192, 203));
		panel_3.setLayout(null);
		panel_3.setBounds(937, 36, 276, 118);
		add(panel_3);
		
		JLabel lblTotalPosts = new JLabel("Total Posts");
		lblTotalPosts.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPosts.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblTotalPosts.setBounds(0, 84, 276, 34);
		panel_3.add(lblTotalPosts);
		
		JLabel lblNewLabel_1_3 = new JLabel(totalPosts + "");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 70));
		lblNewLabel_1_3.setBounds(0, 0, 276, 81);
		panel_3.add(lblNewLabel_1_3);
		
		panel_chart = new JPanel();
		panel_chart.setBounds(32, 222, 750, 502);
		add(panel_chart);
		panel_chart.setLayout(null);
		
		chart = new Chart();
		chart.setBackground(new Color(255, 255, 255));
		chart.setLocation(10, 5);
		chart.setSize(730, 487);
		panel_chart.add(chart);
		
		chart.addLegend("Number of meetings per month", new Color(38, 190, 51));
			
		panel_donut = new JPanel();
		panel_donut.setBackground(Color.WHITE);
		panel_donut.setLayout(null);
		panel_donut.setBounds(792, 222, 421, 502);
		add(panel_donut);
		
		pieChart = new PieChart();
		pieChart.setBounds(0, 0, 421, 420);
		
		pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);
		int[] meetingStatus = serviceCommunity.getMeetingStatus();
		pieChart.addData(new ModelPieChart("Completed meeting", meetingStatus[0], new Color(23, 126, 238)));
		pieChart.addData(new ModelPieChart("Upcoming meeting", meetingStatus[1], new Color(221, 65, 65)));
        
        panel_donut.add(pieChart);
        
        JLabel lblNewLabel_2 = new JLabel("Meeting status");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 27));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(10, 430, 401, 62);
        panel_donut.add(lblNewLabel_2);
		
		thongke();
	}
	
	public void thongke() {
		int[] numberOfMeetingPerMonth = serviceCommunity.getMonthlyMeetingStatistics();
        for(int i = 1; i <= 12; i++) {
        	chart.addData(new ModelChart(month[i], new double[]{numberOfMeetingPerMonth[i-1]}));
        }
        chart.start();
	}
	
	public void load() {
		List<Model_User_Account> Userlist = serviceUser.getUser();
		for (Model_User_Account user : Userlist) {
			totalUsers++;
		}
		
		List<Model_Project_Manager> Communitylist = serviceCommunity.getProjectManager();
		for (Model_Project_Manager project : Communitylist) {
			totalProjects++;
			totalMeetings += project.getTotalMeetings();
			totalPosts += project.getTotalPosts();
		}
	}

}
