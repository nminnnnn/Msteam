package controller.Community;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.community.Model_Project;
import service.Service;
import view.CommunityUI.form.Menu_Left;

public class Controller_MenuLeft implements ActionListener{
	private Menu_Left menuLeft;
	
	public Controller_MenuLeft(Menu_Left menuLeft) {
		this.menuLeft = menuLeft;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuLeft.getBt_add()) {
			String nameProject = JOptionPane.showInputDialog(menuLeft, "Input name project");
			Model_Project project = new Model_Project(0, nameProject);
			Service.getInstance().addProject(project.toJsonObject());
		}
		
	}

}
