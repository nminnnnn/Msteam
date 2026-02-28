package view.ChatUI.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import view.ChatUI.swing.Line;

public class Chat_Date extends javax.swing.JLayeredPane {
    private JLabel lbDate;
    private Line line1;
    private Line line2; 
    
    public Chat_Date() {
    	lbDate = new JLabel();
        line1 = new Line();
        line2 = new Line();

        lbDate.setForeground(new Color(191, 191, 191));
        lbDate.setHorizontalAlignment(SwingConstants.CENTER);
        lbDate.setFont(new Font("tohoma", Font.PLAIN, 15));
        lbDate.setText("01/04/2024");

        line1.setForeground(new Color(191, 191, 191));

        line2.setForeground(new Color(191, 191, 191));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(line1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line2, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDate)
                        .addComponent(line1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
    }

    public void setDate(String date) {
        lbDate.setText(date);
    }

}
