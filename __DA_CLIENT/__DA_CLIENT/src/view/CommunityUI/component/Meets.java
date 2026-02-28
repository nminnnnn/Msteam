package view.CommunityUI.component;

import javax.swing.JPanel;

import model.community.Model_Meeting;
import net.miginfocom.swing.MigLayout;
import view.CommunityUI.form.Meeting_room;

public class Meets extends JPanel{
	
	public Meets() {
		setLayout(new MigLayout("fillx", "100[fill]100", "0[100]15"));	
	}
	
	public void addMeet(Model_Meeting meeting) {
		add(new Item_meet(meeting) , "wrap");
        repaint();
        revalidate();
	}
	
    public boolean hasMeet() {
        return getComponentCount() > 0;
    }
}
