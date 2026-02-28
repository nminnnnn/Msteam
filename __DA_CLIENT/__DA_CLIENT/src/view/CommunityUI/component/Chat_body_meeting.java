package view.CommunityUI.component;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;
import model.Chat.Model_User_Account;
import model.community.Model_Message_Meeting;
import net.miginfocom.swing.MigLayout;
import service.Service;
import view.ChatUI.component.Chat_Date;
import view.ChatUI.component.Chat_Left;
import view.ChatUI.component.Chat_Left_With_Profile;
import view.ChatUI.component.Chat_Right;

public class Chat_body_meeting extends JPanel {
    private JPanel body;
    private JScrollPane sp;
	
    public Chat_body_meeting() {
    	sp = new JScrollPane();
        body = new JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new Color(236, 247, 252));

        GroupLayout bodyLayout = new GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        ); 
        
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        
        updateScroll();
    }
    
    public void addItemLeft(Model_Message_Meeting data) {
        Chat_Left item = new Chat_Left();
        item.setUserProfile(data.getName());
//    	Chat_Left_With_Profile item = new Chat_Left_With_Profile(user);
        item.setText(data.getMessage());
//        item.setTime(data.getTime());
        body.add(item, "wrap, w 100::80%");
        repaint();
        revalidate();
        updateScroll();  
    }
    
    public void addItemRight(Model_Message_Meeting data) {
        Chat_Right item = new Chat_Right();        
        item.setText(data.getMessage());
        body.add(item, "wrap, al right, w 100::80%");
        repaint();
        revalidate();
//        item.setTime(data.getTime());
        updateScroll();
//        scrollToBottom();
    }
    
    public void addDate(String date) {
    	Chat_Date item = new Chat_Date();
    	item.setDate(date);
    	body.add(item, "wrap, al center");
    	body.repaint();
    	body.revalidate();
    	updateScroll();
    }
    
    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    public void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
}