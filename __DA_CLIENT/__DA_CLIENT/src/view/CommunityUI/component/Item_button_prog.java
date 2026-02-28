package view.CommunityUI.component;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;

public class Item_button_prog extends JPanel{
	private JButton bt_new;
	private JButton bt_import;
	private JButton bt_export;

	public Item_button_prog() {
//		setLayout(new MigLayout("fillx", "100[fill]40", "15[]15"));
		
		JPanel panel_button_prog = new JPanel();
		panel_button_prog.setBorder(new EmptyBorder(0, 20, 10, 10));
		
		bt_new = new JButton("NEW");
		bt_new.setBackground(new Color(147, 112, 219));
		bt_new.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		bt_import = new JButton("IMPORT");
		bt_import.setBackground(new Color(147, 112, 219));
		bt_import.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		bt_export = new JButton("EXPORT");
		bt_export.setBackground(new Color(147, 112, 219));
		bt_export.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(bt_new, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(175)
					.addComponent(bt_import, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(165)
					.addComponent(bt_export, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(bt_import, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(bt_export, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(bt_new, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
					.addGap(23))
		);
		setLayout(groupLayout);
//		add(panel_button_prog);
	}

	public JButton getBt_new() {
		return bt_new;
	}

	public JButton getBt_import() {
		return bt_import;
	}

	public JButton getBt_export() {
		return bt_export;
	}
	

}
