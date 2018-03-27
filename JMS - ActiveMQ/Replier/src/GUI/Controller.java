package GUI;


import JMS.replier.Replier;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {
    public ListView messagesListView;
    public TextField messageField;
    Replier replier = new Replier();

    public void buttonSendClicker() {
        String messageBody = messageField.getText();
        replier.sendReply(messageBody);

    }
}
