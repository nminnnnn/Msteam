package view.ChatUI.event;

import model.Chat.Model_File;
import model.Chat.Model_Receive_Message;
import model.Chat.Model_Send_Message;

public interface EventChat {

    public void sendMessage(Model_Send_Message data);
    
    public void sendMessage(Model_File data);

    public void receiveMessage(Model_Receive_Message data);
    
    public void receiveMessage(Model_File data);
}
