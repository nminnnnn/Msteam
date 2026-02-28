package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Model_User_Account;
import utils.ImageAvatar;
import utils.ImageDecoder;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class ItemLog extends JLayeredPane {
    private JLabel lb;
    private String user;
    private String log;
	private JTextPane textPane;
	private JLabel lb_time;
	
    public ItemLog(String user, String log) {
    	this.user = user;
    	this.log = log;
        initComponents();
    }
 
    private void initComponents() {
        lb = new JLabel();
        lb.setForeground(Color.GREEN);
        lb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lb.setFont(new Font("Tahoma", Font.BOLD, 18)); // NOI18N
        lb.setText(user);
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formattedDateTime = now.format(formatter);
        
        lb_time = new JLabel(formattedDateTime);
        lb_time.setForeground(Color.DARK_GRAY);
        lb_time.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lb_time.setHorizontalAlignment(SwingConstants.RIGHT);
        
        textPane = new JTextPane();
        textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textPane.setText(log);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(lb, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
        			.addGap(18)
        			.addComponent(lb_time, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
        			.addContainerGap())
        		.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lb, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lb_time, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        this.setBackground(Color.WHITE);
        this.setLayout(layout);
    }

	public String getUser() {
		return user;
	}

	public String getLog() {
		return log;
	}
    
    
}
