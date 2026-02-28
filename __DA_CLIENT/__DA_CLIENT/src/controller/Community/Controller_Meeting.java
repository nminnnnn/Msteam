package controller.Community;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.CommunityUI.form.Body;

public class Controller_Meeting implements ActionListener{
	private Body body;
	
	public Controller_Meeting(Body body) {
		super();
		this.body = body;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == body.getTitle().getBt_newMeeting()) {
			body.createMeeting();
		}
	}

}
