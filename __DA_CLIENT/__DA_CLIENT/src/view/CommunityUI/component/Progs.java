package view.CommunityUI.component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.community.Model_Meeting;
import model.community.Model_Prog;
import net.miginfocom.swing.MigLayout;

public class Progs extends JPanel{
	public Progs() {
		setLayout(new MigLayout("fillx", "100[fill]100", "0[]15"));	
	}
	
	public void addProg(Model_Prog prog) {
		add(new Item_Prog(prog) , "wrap");
        repaint();
        revalidate();
	}
	
    public boolean hasMeet() {
        return getComponentCount() > 0;
    }
    
    public List<Model_Prog> getAllProgs() {
        List<Model_Prog> progList = new ArrayList<>();
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof Item_Prog) {
                Item_Prog item = (Item_Prog) getComponent(i);
                progList.add(item.getProg());
            }
        }
        return progList;
    }
}
