package view.ChatUI.form;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import view.ChatUI.event.PublicEvent;
import view.ChatUI.swing.PictureBox;

public class View_Image extends JComponent{
	private JButton cmdSave;
	private PictureBox pic;
	private Icon image;
	
	public View_Image() {
		pic = new PictureBox();
		cmdSave = new JButton();
		
        pic.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                picMousePressed(evt);
            }
        });
        
        cmdSave.setIcon(new ImageIcon(getClass().getResource("/images/icon/save.png"))); 
        cmdSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdSave.setContentAreaFilled(false);
        cmdSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });
        
        pic.setLayer(cmdSave, JLayeredPane.DEFAULT_LAYER);
        
        GroupLayout picLayout = new GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, picLayout.createSequentialGroup()
                .addContainerGap(735, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addContainerGap())
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, picLayout.createSequentialGroup()
                .addContainerGap(464, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pic, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pic, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
	}
	
	public void viewImage(Icon image) {
		this.image = image;
		pic.setImage(image);
		setVisible(true);
	}
	
    private void picMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picMousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            setVisible(false);
        }
    }

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        PublicEvent.getInstance().getEventImageView().saveImage(image);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

}
