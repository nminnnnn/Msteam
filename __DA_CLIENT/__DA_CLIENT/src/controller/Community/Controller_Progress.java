package controller.Community;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CommunityUI.component.Page;

public class Controller_Progress implements ActionListener{
	private Page page;
	
	public Controller_Progress(Page page) {
		this.page = page;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == page.getButton_prog().getBt_new()) {
			page.startNewProg();
		}
		else if(e.getSource() == page.getNewProg().getBt_add()) {
			page.newProg();
		}
		else if(e.getSource() == page.getButton_prog().getBt_export()) {
			System.out.println("export");
			page.exportProg();
		}
		else if(e.getSource() == page.getButton_prog().getBt_import()) {
			page.importProg();
		}
		
	}
}
