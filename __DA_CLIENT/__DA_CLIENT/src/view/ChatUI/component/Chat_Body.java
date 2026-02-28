package view.ChatUI.component;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import model.Chat.Model_File;
import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;
import model.Chat.Model_User_Account;
import net.miginfocom.swing.MigLayout;
import service.Service;

public class Chat_Body extends javax.swing.JPanel {
    private JPanel body;
    private JScrollPane sp;
    private Model_User_Account user;
	
    public Chat_Body() {
    	sp = new JScrollPane();
        body = new JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new Color(236, 247, 252));

        GroupLayout bodyLayout = new GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        ); 
        
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        
//        addItemLeft("Nhiều khi anh mong được một lần nói ra hết tất cả thay vì. Ngồi lặng im nghe em kể về anh ta bằng đôi mắt lấp lánh", "Đính Dương");
//        addItemRight("Đôi lúc em tránh ánh mắt của anh. Vì dường như lúc nào em cũng hiểu thấu lòng anh.");
//        addItemLeft("Ko thể ngắt lời, càng ko thể để giọt lệ nào đc rơi", "Đính Dương", new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
//        addDate("01/04/2024");
//        addItemLeft("Nên anh lùi bước về sau, để thấy em rõ hơn", "Đính Dương");
//        addItemRight("Để có thể ngắm em từ xa âu yếm hơn");
//        addItemLeft("Cả nguồn sống bỗng chốc thu bé lại vừa bằng 1 cô gái", "Đính Dương");
//        addItemRight("Hay anh vẫn sẽ lặng lẽ kế bên", new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
//        addDate("06/04/2024");
//        addItemLeft("Dù ko nắm tay nhưng đường chung mãi mãi", "Đính Dương", new ImageIcon(getClass().getResource("/images/testing/avatar.png")));
//        addItemRight("Và từ ấy ánh mắt anh hồn nhiên đến lạ");
//        addItemFile("", "Dara", "my doc.pdf", "1 MB");
//        addItemFileRight("", "myfile.rar", "15 MB");
        
        updateScroll();
    }

//    public void addItemLeft(String text, String user, Icon... image) {
//        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
//        item.setUserProfile(user);
//        item.setText(text);
//        item.setImage(image);
//        item.setTime();
//        body.add(item, "wrap, w ::80%");
//        //  ::80% set max with 80%
//        body.repaint();
//        body.revalidate();
//        updateScroll();
//    }
    
    public void loadHistory(String history) {
    	System.out.println("loadHistory : \n" + history);
        String[] lines = history.split("\n");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length == 4) {
                int fromUserID = Integer.parseInt(parts[0]);
                String text = parts[1];
                int toUserID = Integer.parseInt(parts[2]);
                String time = parts[3];
                
                if(fromUserID == Service.getInstance().getUser().getUser_Id()) {
                	Model_Send_Message data = new Model_Send_Message(fromUserID, toUserID, text, time);
                	addItemRight(data);
                }
                else {
                	Model_Receive_Message data = new Model_Receive_Message(fromUserID, text, time);
                	addItemLeft(data);
                }
            } else {
                System.out.println("Dòng không hợp lệ: " + line);
            }
        }
    }
    
    public void addItemLeft(Model_Receive_Message data) {
//        Chat_Left item = new Chat_Left();
    	Chat_Left_With_Profile item = new Chat_Left_With_Profile(user);
        item.setText(data.getText());
        item.setTime(data.getTime());
        body.add(item, "wrap, w 100::80%");
        repaint();
        revalidate();
        updateScroll();  
        updateScroll(); 
    }
    
    public void addItemLeft(Model_File data) {
        Chat_Left_File item = new Chat_Left_File(user);
//        item.setUserProfile(data.getName());
        item.setFile(data);
        body.add(item, "wrap, w 100::80%");
        repaint();
        revalidate();
        updateScroll();  
    }
    
//    public void addItemFile(String text, String user, String fileName, String fileSize) {
//        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
//        item.setText(text);
//        item.setFile(fileName, fileSize);
//        item.setTime();
//        item.setUserProfile(user);
//        body.add(item, "wrap, w 100::80%");
//        //  ::80% set max with 80%
//        body.repaint();
//        body.revalidate();
//        updateScroll();
//    }
    
//    public void addItemFileRight(String text, String fileName, String fileSize) {
//        Chat_Right item = new Chat_Right();
//        item.setText(text);
//        item.setFile(fileName, fileSize);
//        item.setTime();
//        body.add(item, "wrap, al right, w 100::80%");
//        //  ::80% set max with 80%
//        body.repaint();
//        body.revalidate();
//        updateScroll();
//    }
    
//    public void addItemRight(String text, Icon... image) {
//        Chat_Right item = new Chat_Right();
//        item.setText(text);
//        item.setImage(image);
//        item.setTime();
//        body.add(item, "wrap, al right, w ::80%");
//        //  ::80% set max with 80%
//        body.repaint();
//        body.revalidate();
//        updateScroll();
//    }
    
    public void addItemRight(Model_Send_Message data) {
        Chat_Right item = new Chat_Right();
        item.setText(data.getText());
        body.add(item, "wrap, al right, w 100::80%");
        repaint();
        revalidate();
        item.setTime(data.getTime());
        updateScroll();
        updateScroll();
    }
    
    public void addItemRight(Model_File data) {
        Chat_Right_File item = new Chat_Right_File();
        item.setFile(data);
        body.add(item, "wrap, al right, w 100::80%");
        repaint();
        revalidate();
        updateScroll();
        updateScroll();
    }
    
    public void addDate(String date) {
    	Chat_Date item = new Chat_Date();
    	item.setDate(date);
    	body.add(item, "wrap, al center");
    	body.repaint();
    	body.revalidate();
    	updateScroll();
    }
    
    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    public void updateScroll() {
        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalScrollBar = sp.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        });
    }
    
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }
}
