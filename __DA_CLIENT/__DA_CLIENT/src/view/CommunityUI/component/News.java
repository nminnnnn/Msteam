package view.CommunityUI.component;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import model.Chat.Model_User_Account;
import model.community.Model_Post;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;

public class News extends JPanel{
	private Model_Project project;
	private Model_User_Account user;
	
	public News(Model_Project project) {
		this.project = project;
		
		setLayout(new MigLayout("fillx", "100[fill]100", "0[]15"));
		
//		add(new Item_post("Đính Dương", "00:30 AM", "15h30 07/04/2005 mọi người họp nhé!"), "wrap");
//		add(new Item_post("Đính Dương", "00:45 AM", "Nên anh lùi bước về sau, để thấy em rõ hơn\r\n"
//				+ "Để có thể ngắm em từ xa âu yếm hơn\r\n"
//				+ "Cả nguồn sống bỗng chốc thu bé lại vừa bằng 1 cô gái\r\n"
//				+ "Hay anh vẫn sẽ lặng lẽ kế bên\r\n"
//				+ "Dù ko nắm tay nhưng đường chung mãi mãi\r\n"
//				+ "Và từ ấy ánh mắt anh hồn nhiên đến lạ"), "wrap");
//		add(new Item_post("Đính Dương", "00:30 AM", "In Java Swing, JLayeredPane is a powerful container that allows you to overlay and manage multiple components within a single container. Each component in a JLayeredPane can occupy a different layer, and you can control their positions and visibility. In this article, we are going to implement the JLayeredPane with different examples. In Java JLayeredPane is a part of javax.swing package."), "wrap");
//		add(new Item_post("Đính Dương", "00:30 AM", "15h30 07/04/2005 mọi người họp nhé!"), "wrap");
//		add(new Item_post("Đính Dương", "00:45 AM", "Nên anh lùi bước về sau, để thấy em rõ hơn\r\n"
//				+ "Để có thể ngắm em từ xa âu yếm hơn\r\n"
//				+ "Cả nguồn sống bỗng chốc thu bé lại vừa bằng 1 cô gái\r\n"
//				+ "Hay anh vẫn sẽ lặng lẽ kế bên\r\n"
//				+ "Dù ko nắm tay nhưng đường chung mãi mãi\r\n"
//				+ "Và từ ấy ánh mắt anh hồn nhiên đến lạ"), "wrap");
//		add(new Item_post("Đính Dương", "00:30 AM", "m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m m "), "wrap");
	}
	
	public void post(Model_Post post) {
		this.add(new Item_post(user, post), "wrap");
    	repaint();
    	revalidate();
	}

	public Model_User_Account getUser() {
		return user;
	}

	public void setUser(Model_User_Account user) {
		this.user = user;
	}
	
	
	
	
}
