package view.ChatUI.component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import model.ImageDecoder;
import model.Chat.Model_User_Account;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.ImageAvatar;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Item_People extends JPanel {
	private ImageAvatar imageAvatar1;
    private JLabel lb;
    private boolean mouseOver;
    private final Model_User_Account user;
	private JLabel lb_status;
	
    public Item_People(Model_User_Account user) {
    	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	this.user = user;
        initComponents();
        lb.setText(user.getFullName());
        init();
    }
    
    public void init() {
    	addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(255, 255, 255));
                mouseOver = false;
            }
            @Override
            public void mouseReleased(MouseEvent me) {
                if (mouseOver) {
                    PublicEvent.getInstance().getEventMain().selectUser(user);
                }
            }
        });
    }

    private void initComponents() {
        imageAvatar1 = new ImageAvatar();
        imageAvatar1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lb = new JLabel();
        lb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        imageAvatar1.setBorderSize(0);
//        imageAvatar1.setImage(new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
        imageAvatar1.setImage(ImageDecoder.decodeByteArrayToImageIcon(user.getAvatar()));
        lb.setFont(new java.awt.Font("sansserif", Font.BOLD, 18)); // NOI18N
        lb.setText("Name");
        setBackground(new Color(255, 255, 255));
        
//        lb_status= new JLabel(".");
        lb_status = new JLabel(".");
        lb_status.setSize(5,  5);
        active(user.isStatus());
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lb, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(lb_status, 10, 10, Short.MAX_VALUE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(lb, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lb_status, 10, 10, Short.MAX_VALUE)))
        			.addContainerGap(50, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }
    
    public Model_User_Account getUser() {
        return user;
    }
    
    public void active(boolean active) {
    	user.setStatus(active);
    	if(active) {
//            lb_status.setBackground(Color.GREEN);
//            lb_status.setForeground(Color.GREEN);
            lb_status.setOpaque(true);
            lb_status.setIcon(new ImageIcon(getClass().getResource("/images/icon/online.jpg")));
    	}
    	else {
//            lb_status.setBackground(new Color(255, 255, 255));
//            lb_status.setForeground(new Color(255, 255, 255));
            lb_status.setOpaque(true);
            lb_status.setIcon(new ImageIcon(getClass().getResource("/images/icon/offline.jpg")));
    	}
    }
}
