package controller.Community;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CommunityUI.component.Page;

public class Controller_Post implements ActionListener{
	private Page page;
	
	public Controller_Post(Page page) {
		this.page = page;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == page.getNewPost().getBt_newPost()) {
			page.startNewPost();
		}
		else if(e.getSource() == page.getStartNewPost().getBt_dangbai()) {
			page.postNewPost();
		}
		
	}

}
