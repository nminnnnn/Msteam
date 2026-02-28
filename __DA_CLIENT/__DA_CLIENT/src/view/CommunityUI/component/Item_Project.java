package view.CommunityUI.component;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.community.Model_Project;
import view.ChatUI.event.PublicEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Item_Project extends JPanel{
	private JLabel lb_nameProject;
	
	private boolean mouseOver;
	Model_Project project;
	
	public Item_Project(Model_Project project) {
		this.project = project;
		lb_nameProject = new JLabel(project.getProjectName());
		lb_nameProject.setHorizontalAlignment(SwingConstants.LEFT);
		lb_nameProject.setFont(new Font("Tahoma", Font.BOLD, 23));
		
		setBackground(Color.white);
		setBorder(new EmptyBorder(10, 20, 10, 10));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lb_nameProject, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(lb_nameProject, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
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
                    PublicEvent.getInstance().getEventMain().selectedProject(project);
                }
            }
        });
    }
}
