package view.ChatUI.component;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.PictureBox;

public class Chat_Image extends JLayeredPane{
	public Chat_Image(boolean right) {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        
        setLayout(new MigLayout("", "0[" + (right ? "right" : "left") + "]0", "3[]3"));
	}
	
	public void addImage(Icon... images) {
		for(Icon image : images) {
			PictureBox pic = new PictureBox();
			pic.setPreferredSize(getAutoSize(image, 200, 200));
			pic.setImage(image);
			addEvent(pic, image);
			add(pic, "wrap");
		}
	}
	
	
    private void addEvent(Component com, Icon image) {
        com.setCursor(new Cursor(Cursor.HAND_CURSOR));
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    PublicEvent.getInstance().getEventImageView().viewImage(image);
                }
            }
        });
    }
	
    private Dimension getAutoSize(Icon image, int w, int h) {
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.min(xScale, yScale);
        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        return new Dimension(width, height);
    }
}
