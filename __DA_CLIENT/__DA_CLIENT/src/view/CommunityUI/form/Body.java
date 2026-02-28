package view.CommunityUI.form;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.CardLayout.CardLayout_Community;
import controller.Community.Controller_Meeting;
import controller.Community.Controller_Member;
import model.Chat.Model_User_Account;
import model.community.Model_Meeting;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.CommunityUI.component.NewPost;
import view.CommunityUI.component.News;
import view.CommunityUI.component.Page;
import view.CommunityUI.component.Title;

public class Body extends JPanel{
	private Title title;
	private Page page;
	
	private Model_Project project;
	private ListMember member;
	
	public Body(Model_Project project) {
		this.project = project;
		title = new Title(project);
		page = new Page(project);
		
		setBackground(new Color(242, 242, 242));
		
		
		setLayout(new MigLayout("fillx", "0[fill]0", "0[50]0[100%, fill]0"));
		
		this.add(title, "wrap");
		this.add(page);
		
		Controller_Member action_member = new Controller_Member(this);
		title.getBt_member().addActionListener(action_member);
		Controller_Meeting action_meeting = new Controller_Meeting(this);
		title.getBt_newMeeting().addActionListener(action_meeting);
		cardLayout();
	}
	
	public void memberProject() {
		JDialog dialog = new JDialog();
		member = new ListMember(project);
		Service.getInstance().listMember(project.getProjectId());
		dialog.setSize(310, 400);
		dialog.setLocationRelativeTo(null);
		dialog.add(member);
		dialog.setVisible(true);
	}
	
	public void createMeeting() {
//		JDialog dialog = new JDialog();
//		dialog.setSize(300, 300);
//		dialog.setLocationRelativeTo(null);
//		dialog.add(new Create_meeting(project));
//		dialog.setVisible(true);
		Create_meeting newMeeting = new Create_meeting(project);
		int option = JOptionPane.showOptionDialog(this, newMeeting, "CREATE NEW MEETING", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (option == JOptionPane.OK_OPTION) {
        	Model_Meeting meeting = new Model_Meeting(0, project.getProjectId(), newMeeting.getTf_title().getText(), newMeeting.getTime() );
        	Service.getInstance().addMeeting(meeting.toJsonObject("addMeeting"));
        }
	}
	
	public void setUser(Model_User_Account user) {
		page.setUser(user);
	}
	
	public void cardLayout() {
		CardLayout_Community action_cardLayout_Community = new CardLayout_Community(this);
		getTitle().getBt_post().addActionListener(action_cardLayout_Community);
		getTitle().getBt_event().addActionListener(action_cardLayout_Community);
		getTitle().getBt_progress().addActionListener(action_cardLayout_Community);
	}
	
	

	public Title getTitle() {
		return title;
	}

	public Page getPage() {
		return page;
	}

	public Model_Project getProject() {
		return project;
	}

	public void setProject(Model_Project project) {
		this.project = project;
		title.setProject(project);
		page.setProject(project);
	}

	public ListMember getMember() {
		return member;
	}

	public void setMember(ListMember member) {
		this.member = member;
	}
	
	
	
	

	
	
}
