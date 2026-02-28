package view.CommunityUI.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.ImageDecoder;
import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import view.ChatUI.swing.ImageAvatar;
import view.ChatUI.swing.JIMSendTextPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NewPost extends JPanel{
	private ImageAvatar imageAvatar;
	private JButton bt_newPost;
	private Model_User_Account user;

	public NewPost() {	
		setLayout(new MigLayout("fillx", "100[fill]40", "15[]15"));
		
		JPanel panel_newPost = new JPanel();
		panel_newPost.setBorder(new EmptyBorder(0, 20, 10, 10));
		
		imageAvatar = new ImageAvatar();
		imageAvatar.setBorderSize(0);
		imageAvatar.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
		
		bt_newPost = new JButton("Start a new post...");
		bt_newPost.setFont(new Font("Tahoma", Font.BOLD, 22));
		bt_newPost.setHorizontalAlignment(SwingConstants.LEFT);
		bt_newPost.setBorder(null);
		bt_newPost.setBackground(Color.white);
		
		
		GroupLayout groupLayout = new GroupLayout(panel_newPost);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(imageAvatar, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bt_newPost, GroupLayout.PREFERRED_SIZE, 706, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(bt_newPost, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
						.addComponent(imageAvatar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
		);
		panel_newPost.setLayout(groupLayout);
		panel_newPost.setBackground(Color.white);
		
		add(panel_newPost);
	}

	public JButton getBt_newPost() {
		return bt_newPost;
	}

	public Model_User_Account getUser() {
		return user;
	}

	public void setUser(Model_User_Account user) {
		this.user = user;
		imageAvatar.setImage(ImageDecoder.decodeByteArrayToImageIcon(user.getAvatar()));
	}

	
	
}
