package controller.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.Service;
import view.MainUI;
import view.ChatUI.event.PublicEvent;

public class CardLayout_MainUI implements ActionListener{
	private MainUI main;
	
	public CardLayout_MainUI(MainUI main) {
		super();
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == main.getButton_community()) {
			main.getCardLayout_MainUI().show(main.getPanel_card(), "panel_card_community");
			Service.getInstance().getMain().getHome_community().getMenuLeft().getPanel_menu_list().removeAll();
			Service.getInstance().listProject();
		}
		else if(e.getSource() == main.getButton_chat()) {
			main.getCardLayout_MainUI().show(main.getPanel_card(), "panel_card_chat");
		}
		else if(e.getSource() == main.getButton_calender()) {
			main.getCardLayout_MainUI().show(main.getPanel_card(), "panel_card_calender");
    		main.getCalendarUI().removeAllItems();
			Service.getInstance().listCalendar();
		}
		else if(e.getSource() == main.getButton_user()) {
			main.getCardLayout_MainUI().show(main.getPanel_card(), "panel_card_user");
		}	
	}

}
