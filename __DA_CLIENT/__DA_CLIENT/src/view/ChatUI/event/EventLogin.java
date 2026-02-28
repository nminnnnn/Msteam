package view.ChatUI.event;

import model.Chat.Model_Register;

public interface EventLogin {
    public void login();

    public void register(Model_Register data, EventMessage message);

    public void goRegister();

    public void goLogin();	
}
