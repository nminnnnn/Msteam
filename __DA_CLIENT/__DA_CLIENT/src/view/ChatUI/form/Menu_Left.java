package view.ChatUI.form;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import view.MainUI;
import view.ChatUI.component.Item_People;
import view.ChatUI.event.EventMenuLeft;
import view.ChatUI.event.PublicEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import model.Chat.Model_User_Account;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.SwingConstants;

public class Menu_Left extends JPanel{
	private JLayeredPane panel_menu_list;
	private List<Model_User_Account> userAccount;

	public Menu_Left() {
		setSize(300, 803);
		setLayout(new MigLayout("fillx, filly", "0[300]0", "0[50]0[100%,fill]0"));
//		JPanel panel_menu = new JPanel();
//		add(panel_menu, "width 300:300:300, wrap");
//		panel_menu.setLayout(new GridLayout(1, 2, 0, 0));
		
		JLabel label = new JLabel("MESSAGE");
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("Gill Sans Ultra Bold Condensed", Font.BOLD, 26));
		label.setBackground(new Color(0, 191, 255));
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, "width 300:300:300, height 50:50:50, wrap");
		
//		JButton bt_chat2P = new JButton("");
//		bt_chat2P.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showPeople();
//			}
//		});
//		panel_menu.add(bt_chat2P);
//		
//		JButton bt_chatGroup = new JButton("");
//		bt_chatGroup.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				showGroup();
//			}
//		});
//		panel_menu.add(bt_chatGroup);
//		
//		bt_chat2P.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/chat2p.png"))).getImage())));
//		bt_chatGroup.setIcon(new ImageIcon((new ImageIcon((MainUI.class.getResource("/images/icon/chatgroup.png"))).getImage())));
//		
		
		panel_menu_list = new JLayeredPane();
		panel_menu_list.setLayout(new MigLayout("fillx", "2[300]2", "3[]3"));
		
//		JTextField tf_searchUser = new JTextField();
//		tf_searchUser.setFont(new Font("SansSerif", Font.PLAIN, 18));
//		tf_searchUser.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String user = tf_searchUser.getText();
//                if(searchUser(user)) {
////                	PublicEvent.getInstance().getEventMenuLeft().newUser(list_user);
//                }
//                else {
//                	JOptionPane.showMessageDialog(panel_menu_list, "NOT FOUND USER");
//                }
//            }
//        });
//		add(tf_searchUser, "width 300:300:300, height 35:35:35, wrap");
		
		userAccount = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
//            @Override
//            public void newUser(List<Model_User_Account> users) {
//                for (Model_User_Account d : users) {
//                    userAccount.add(d);
//                    panel_menu_list.add(new Item_People(d.getUserName()), "width 296:296:296, height 50:50:50, wrap");
//                    panel_menu_list.repaint();
//                    panel_menu_list.revalidate();
//                }
//            }
            @Override
            public void newUser(Model_User_Account d) {	
                    userAccount.add(d);
                    panel_menu_list.add(new Item_People(d), "width 285:285:285, height 50:50:50, wrap");
                    panel_menu_list.repaint();
                    panel_menu_list.revalidate();
            }
        });
		
		JScrollPane jScrollPane = new JScrollPane(panel_menu_list);
		jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(jScrollPane);
		
//		showPeople();
		setBackground(Color.blue);
		panel_menu_list.removeAll();
	}
	
	public void loadActive(int userId, boolean isActive) {
		Component[] components = panel_menu_list.getComponents();
		for (Component component : components) {
		    if (component instanceof Item_People) {
		        Item_People item = (Item_People) component;
		        if(item.getUser().getUser_Id() == userId) {
		        	item.active(isActive);
		        }
		    }
		}
	}
	
	public void showPeople() {
		panel_menu_list.removeAll();
		
        for (Model_User_Account d : userAccount) {
        	panel_menu_list.add(new Item_People(d), "width 296:296:296, height 50:50:50, wrap");
        }
        
//		panel_menu_list.add(new Item_People("Đính Dương"), "width 296:296:296, height 50:50:50, wrap");
//		panel_menu_list.add(new Item_People("Lê Hữu Anh Tú"), "width 296:296:296, height 50:50:50, wrap");
//		panel_menu_list.add(new Item_People("Cao Hoàng Phước Bảo"), "width 296:296:296, height 50:50:50, wrap");
//		for(int i = 1; i <= 20; i++) {
//			panel_menu_list.add(new Item_People("People " + i), "width 296:296:296, height 50:50:50, wrap");
//		}
		panel_menu_list.repaint();
		panel_menu_list.revalidate();
	}
	
	public void showGroup() {
//		panel_menu_list.removeAll();
//		for(int i = 1; i <= 10; i++) {
//			panel_menu_list.add(new Item_People("Group " + i), "width 296:296:296, height 50:50:50, wrap");
//		}
//		panel_menu_list.repaint();
//		panel_menu_list.revalidate();
	}
	
	public boolean searchUser(String userName) {
		for(Model_User_Account user : userAccount) {
			if(user.getUserName().equalsIgnoreCase(userName)) {
				return true;
			}
		}
		return false;
	}
	
	public Model_User_Account getUser(int userId) {
		for(Model_User_Account user : userAccount) {
			if(user.getUser_Id() == userId) {
				return user;
			}
		}
		return null;
	}

	public JLayeredPane getPanel_menu_list() {
		return panel_menu_list;
	}

	public void setPanel_menu_list(JLayeredPane panel_menu_list) {
		this.panel_menu_list = panel_menu_list;
	}
	
	
}
