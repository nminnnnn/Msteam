package view.ChatUI.event;

import model.Chat.Model_User_Account;
import model.community.Model_Project;

public interface EventMain {

    public void initChat();
    
    public void selectUser(Model_User_Account user);

    public void updateUser(Model_User_Account user);
    
    public void selectedProject(Model_Project project);
    
    
}
