package controller.CardLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.Service;
import view.CommunityUI.form.Body;

public class CardLayout_Community implements ActionListener{
	private Body body;
	
	public CardLayout_Community(Body body) {
		super();
		this.body = body;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == body.getTitle().getBt_post()) {
			body.getPage().getCardLayout_Page().show(body.getPage(), "panel_post");
			
			body.getTitle().getBt_post().setBackground(new Color(150, 220, 248));
			body.getTitle().getBt_event().setBackground(new Color(242, 242, 242));
			body.getTitle().getBt_progress().setBackground(new Color(242, 242, 242));
		}
		else if(e.getSource() == body.getTitle().getBt_event()) {
			body.getPage().getCardLayout_Page().show(body.getPage(), "panel_event");
			
			body.getTitle().getBt_post().setBackground(new Color(242, 242, 242));
			body.getTitle().getBt_event().setBackground(new Color(150, 220, 248));
			body.getTitle().getBt_progress().setBackground(new Color(242, 242, 242));
			
			if(!body.getPage().getMeets().hasMeet()) {
				Service.getInstance().listMeeting(body.getProject().getProjectId());
			}
		}
		else if(e.getSource() == body.getTitle().getBt_progress()) {
			System.out.println("button progress");
			body.getPage().getCardLayout_Page().show(body.getPage(), "panel_prog");
			
			body.getTitle().getBt_post().setBackground(new Color(242, 242, 242));
			body.getTitle().getBt_event().setBackground(new Color(242, 242, 242));
			body.getTitle().getBt_progress().setBackground(new Color(150, 220, 248));
			body.getTitle().getBt_progress().setOpaque(true);
			
			if(!body.getPage().getProgs().hasMeet()) {
				Service.getInstance().listProg(body.getProject().getProjectId());
			}
		}
		
	}

}
