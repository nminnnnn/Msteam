package view.ChatUI.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class Chat_File extends JPanel{
	private JPanel jPanel;
	private JLabel lbFileName;
	private JLabel lbFileSize;
	
	public Chat_File() {
		jPanel = new JPanel();
		lbFileName = new JLabel();
		lbFileSize = new JLabel();
		
		lbFileName.setFont(new Font("tohoma", Font.BOLD, 20));
		lbFileSize.setFont(new Font("tohoma", Font.PLAIN, 15));
		
		jPanel.setOpaque(false);
		jPanel.setLayout(new GridLayout(2, 1));
		
		lbFileName.setText("My File Name.file");
		jPanel.add(lbFileName);
		
		lbFileSize.setForeground(new Color(7, 98, 153));
		lbFileSize.setText("100 MB");
		jPanel.add(lbFileSize);
		
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        
        setOpaque(false);
	}
	
    public void setFile(String fileName, String size) {
        lbFileName.setText(fileName);
        lbFileSize.setText(size);
    }
}
