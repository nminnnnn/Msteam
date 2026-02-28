package view.ChatUI.event;

import model.Chat.Model_Message;

public interface EventMessage {
	public void callMessage(Model_Message message);
}
