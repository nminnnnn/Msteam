package view.CommunityUI.component;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import controller.Community.Controller_Post;
import controller.Community.Controller_Progress;
import model.Model_XML;
import model.Chat.Model_User_Account;
import model.community.Model_Post;
import model.community.Model_Prog;
import model.community.Model_Project;
import net.miginfocom.swing.MigLayout;
import service.Service;

public class Page extends JPanel{
	private News news;
	private NewPost newPost;
	private JPanel panel_post;
	private JPanel panel_event;
	private CardLayout cardLayout_Page;
	private Meets meets;
	private StartNewPost startNewPost;
	private Controller_Post action_post;
	private JDialog dialog;
	private Model_Project project;
	private JScrollPane sp;
	private JPanel panel_prog;
	private Progs progs;
	private Item_button_prog button_prog;
	private NewProg newProg;
	private Controller_Progress action_prog;
	private JDialog dialog2;
	private JScrollPane sp2;
	private JScrollPane sp3;
	
	public Page(Model_Project project) {
		this.project = project;
		action_post = new Controller_Post(this);
		action_prog = new Controller_Progress(this);
		
		panel_post = new JPanel();
		panel_event = new JPanel();
		panel_prog = new JPanel();
		
		cardLayout_Page = new CardLayout();
		setLayout(cardLayout_Page);
		add(panel_post, "panel_post");
		add(panel_event, "panel_event");
		add(panel_prog, "panel_prog");
		
		news = new News(project);
		newPost = new NewPost();
		meets = new Meets();
		progs = new Progs();
		button_prog = new Item_button_prog();
		
		newPost.getBt_newPost().addActionListener(action_post);
		button_prog.getBt_new().addActionListener(action_prog);
		button_prog.getBt_import().addActionListener(action_prog);
		button_prog.getBt_export().addActionListener(action_prog);
		
		panel_post.setLayout(new MigLayout("fillx", "0[fill]0", "0[120!]0[100%, fill]0"));
		
		panel_post.add(newPost, "wrap");
		sp = new JScrollPane(news);
		sp.setBorder(null);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_post.add(sp);	
		
		panel_event.setLayout(new MigLayout("fillx", "0[fill]0", "15[100%, fill]0"));
		sp2 = new JScrollPane(meets);
		sp2.setBorder(null);
		sp2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_event.add(sp2);
		
		panel_prog.setLayout(new MigLayout("fillx", "0[fill]0", "0[70!]0[100%, fill]0"));
		panel_prog.add(button_prog, "wrap");
		sp3 = new JScrollPane(progs);
		sp3.setBorder(null);
		sp3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_prog.add(sp3);
		
		newProg = new NewProg();
		
	}
	
	public void startNewPost() {
		startNewPost = new StartNewPost(Service.getInstance().getUser());
		startNewPost.getBt_dangbai().addActionListener(action_post);
//		result = JOptionPane.showOptionDialog(this, startNewPost, null, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		dialog = new JDialog();
		dialog.setLayout(new GridLayout(1,1));
		dialog.setSize(800, 620);
		dialog.setLocationRelativeTo(null);
		dialog.add(startNewPost);
		dialog.setVisible(true);
	}
		
	public void postNewPost() {
		String userName = Service.getInstance().getUser().getFullName();
		byte[] avatar = Service.getInstance().getUser().getAvatar();
		String content = startNewPost.getTextArea().getText();
	
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String timing = dateFormat.format(currentTime);
        Model_Post post = new Model_Post(0, project.getProjectId(), userName, avatar , timing, content);
        Service.getInstance().postNews(post.toJsonObject("postNews"));
//		news.post(post); 
        updateScroll();
		dialog.dispose();
	}
	
	public void startNewProg() {
		newProg = new NewProg();
		newProg.getBt_add().addActionListener(action_prog);
//		result = JOptionPane.showOptionDialog(this, startNewPost, null, JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
		dialog2 = new JDialog();
		dialog2.setLayout(new GridLayout(1,1));
		dialog2.setSize(800, 550);
		dialog2.setLocationRelativeTo(null);
		dialog2.add(newProg);
		dialog2.setVisible(true);
	}
	
	public void newProg() {
		String content = newProg.getTextArea().getText();	
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        String timing = dateFormat.format(currentTime);
        Model_Prog prog = new Model_Prog(0, project.getProjectId(), content , timing);
        Service.getInstance().newProg(prog.toJsonObject("newProg"));
        updateScroll();
		dialog2.dispose();
	}
	
	public void exportProg() {
		List<Model_Prog> progList = progs.getAllProgs();
		Model_XML.exportToXML(progList);
	}
	
	public void importProg() {
		List<Model_Prog> importedList = Model_XML.importFromEncryptedXML();
        for (Model_Prog prog : importedList) {
            progs.addProg(prog);
        }
	}
	
    public void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            sp.revalidate(); 
            sp.repaint(); 
        });
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp2.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            sp2.revalidate(); 
            sp2.repaint(); 
        });
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp3.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            sp3.revalidate(); 
            sp3.repaint(); 
        });
    }
    
	public void setUser(Model_User_Account user) {
		news.setUser(user);
		newPost.setUser(user);
	}

	public CardLayout getCardLayout_Page() {
		return cardLayout_Page;
	}

	public void setCardLayout_Page(CardLayout cardLayout_Page) {
		this.cardLayout_Page = cardLayout_Page;
	}

	public NewPost getNewPost() {
		return newPost;
	}

	public StartNewPost getStartNewPost() {
		return startNewPost;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Model_Project getProject() {
		return project;
	}

	public void setProject(Model_Project project) {
		this.project = project;
	}

	public Meets getMeets() {
		return meets;
	}

	public void setMeets(Meets meets) {
		this.meets = meets;
	}

	public Item_button_prog getButton_prog() {
		return button_prog;
	}

	public JDialog getDialog2() {
		return dialog2;
	}

	public NewProg getNewProg() {
		return newProg;
	}

	public Progs getProgs() {
		return progs;
	}
	
	
}
