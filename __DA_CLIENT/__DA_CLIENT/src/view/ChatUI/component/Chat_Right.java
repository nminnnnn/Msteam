package view.ChatUI.component;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JLayeredPane;

public class Chat_Right extends JLayeredPane{
	
	private Chat_Item txt;
	
	public Chat_Right() {
		txt = new Chat_Item();
		setLayer(txt, JLayeredPane.DEFAULT_LAYER);
		
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(txt, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(txt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		txt.setBackground(new Color(179, 233, 255));
	}
	
    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
    }

    public void setImage(Icon... image) {
        txt.setImage(true, image);
    }
    
    public void setUserProfile(String user) {
    	txt.setUserProfile(user);
    }

    public void setTime(String time) {
//        Date currentTime = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        String formattedTime = dateFormat.format(currentTime);
        txt.setTime(time); 
    }
    
    public void setFile(String fileName, String fileSize) {
        txt.setFile(fileName, fileSize);
    }

}
