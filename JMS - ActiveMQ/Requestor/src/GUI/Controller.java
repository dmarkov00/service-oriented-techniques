package GUI;

import JMS.requestor.Requestor;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class Controller {

    public ListView messagesListView;
    public TextField messageField;

    public Requestor requestor = new Requestor();

    public void buttonSendClicker() {

        String messageBody = messageField.getText();
        requestor.sendRequest(messageBody);

        messagesListView.getItems().add(messageBody);


//  this one for later       Object selected = messagesListView.getSelectionModel().getSelectedItem();
    }

}

// Initializable