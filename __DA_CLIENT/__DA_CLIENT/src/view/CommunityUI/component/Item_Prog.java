package view.CommunityUI.component;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import model.ImageDecoder;
import model.Chat.Model_User_Account;
import model.community.Model_Post;
import model.community.Model_Prog;
import view.ChatUI.swing.ImageAvatar;
import view.ChatUI.swing.JIMSendTextPane;
import javax.swing.SwingConstants;

public class Item_Prog extends JPanel{
	private Model_Prog prog;

	public Item_Prog(Model_Prog prog) {
		this.prog = prog;
		setBorder(new EmptyBorder(0, 20, 10, 10));
		
		JPanel panel_title = new JPanel();
		
		JPanel panel_content = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_content, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_title, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_content, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JIMSendTextPane lb_content = new JIMSendTextPane();
		lb_content.setText(prog.getContent());
		lb_content.setEditable(false);
		lb_content.setPreferredSize(new Dimension(lb_content.getPreferredSize().width, lb_content.getPreferredSize().height));
		lb_content.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GroupLayout gl_panel_content = new GroupLayout(panel_content);
		gl_panel_content.setHorizontalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addComponent(lb_content, GroupLayout.DEFAULT_SIZE, 978, Short.MAX_VALUE)
		);
		gl_panel_content.setVerticalGroup(
			gl_panel_content.createParallelGroup(Alignment.LEADING)
				.addComponent(lb_content, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
		);
		panel_content.setLayout(gl_panel_content);
		
		JLabel lb_time = new JLabel(prog.getTime());
		lb_time.setForeground(new Color(147, 112, 219));
		lb_time.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_time.setHorizontalAlignment(SwingConstants.LEFT);
		GroupLayout gl_panel_title = new GroupLayout(panel_title);
		gl_panel_title.setHorizontalGroup(
			gl_panel_title.createParallelGroup(Alignment.LEADING)
				.addComponent(lb_time, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
		);
		gl_panel_title.setVerticalGroup(
			gl_panel_title.createParallelGroup(Alignment.TRAILING)
				.addComponent(lb_time, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
		);
		panel_title.setLayout(gl_panel_title);
		setLayout(groupLayout);
		
		setBackground(Color.white);
		panel_content.setBackground(Color.white);
		panel_title.setBackground(Color.white);
	}

	public Model_Prog getProg() {
		return prog;
	}

	public void setProg(Model_Prog prog) {
		this.prog = prog;
	}
	
	
}
