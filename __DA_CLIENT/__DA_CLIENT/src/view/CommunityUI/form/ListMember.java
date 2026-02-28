package view.CommunityUI.form;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.Chat.Model_User_Account;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.MainUI;
import view.ChatUI.component.Item_People;
import view.ChatUI.event.EventMenuLeft;
import view.ChatUI.event.PublicEvent;
import view.CommunityUI.component.Item_Project;

public class ListMember extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User_Account> userAccount;
	private Model_Project project;

	public ListMember(Model_Project project) {
		this.project = project;
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[290]0", "0[50]0[100%,fill]0"));
		JPanel panel_menu = new JPanel();
		add(panel_menu, "width 300:300:300, wrap");
		panel_menu.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton bt_chat2P = new JButton("");
		bt_chat2P.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = JOptionPane.showInputDialog("Input userName");
				Service.getInstance().addMember(userName, project.getProjectId());
			}
		});
//		bt_chat2P.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showPeople();
//			}
//		});
		panel_menu.add(bt_chat2P);
		
		bt_chat2P.setIcon(new ImageIcon(ListMember.class.getResource("/images/icon/icon_add_user.png")));
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
		userAccount = new ArrayList<>();
//        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
//            @Override
//            public void newUser(Model_User_Account d) {	
//                    userAccount.add(d);
//                    panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
//                    panel_menu_list.repaint();
//                    panel_menu_list.revalidate();
//            }
//        });
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		
//		showPeople();
		panel_menu_list.setBackground(new Color(240, 240, 240));
		panel_menu_list.removeAll();
		
	}
	
	public void addMember(Model_User_Account user) {
		panel_menu_list.add(new Item_People(user) , "width 295:295:295, height 50:50:50, wrap");
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}
	
	

}
