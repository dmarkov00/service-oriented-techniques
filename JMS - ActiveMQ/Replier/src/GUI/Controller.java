package GUI;


import JMS.replier.LibraryInfo;
import JMS.replier.QuestionAndAnswerData;
import JMS.replier.Replier;
import com.google.gson.Gson;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;


public class Controller {
    @FXML
    public ListView<String> messagesListView;
    @FXML
    public TextField messageField;
    private Replier replier = new Replier();

    private Map<Message, QuestionAndAnswerData> messageData = new LinkedHashMap<>();

    @FXML
    public void initialize() {
        this.listenForQuestions();
    }

    @FXML
    public void buttonSendClicker() {
        String messageBody = messageField.getText();

        // Retrieve the index of the selected item from the list view
        int selectedItemIndex = messagesListView.getSelectionModel().getSelectedIndex();
        if (selectedItemIndex >= 0) {
            // Get the map value for this index (the map values are inserted the same way as the list view values, so the indices match)
            Map.Entry<Message, QuestionAndAnswerData> mapByIndex = this.getPairFromLinkedHashMapByIndex(selectedItemIndex);

            // Send reply
            replier.sendReply(messageBody, mapByIndex.getKey());

            // Updating the map value with the answer string
            QuestionAndAnswerData updatedQuestionAndAnswerData = mapByIndex.getValue();
            updatedQuestionAndAnswerData.setAnswer(messageBody);
            messageData.replace(mapByIndex.getKey(), updatedQuestionAndAnswerData);

            populateListView();
            messageField.setText("");
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Select item form the list view please").show();
        }


    }

    @FXML
    public void buttonSendLibInfoClicker() {

        // Serialization with gson
        LibraryInfo libraryInfo = new LibraryInfo();
        Gson gson = new Gson();
        String libraryInfoJson = gson.toJson(libraryInfo);

        String messageBody = libraryInfoJson;
        // Retrieve the index of the selected item from the list view
        int selectedItemIndex = messagesListView.getSelectionModel().getSelectedIndex();

        if (selectedItemIndex >= 0) {
            // Get the map value for this index (the map values are inserted the same way as the list view values, so the indices match)
            Map.Entry<Message, QuestionAndAnswerData> mapByIndex = this.getPairFromLinkedHashMapByIndex(selectedItemIndex);

            // Send reply
            replier.sendReply(messageBody, mapByIndex.getKey());

            // Updating the map value with the answer string
            QuestionAndAnswerData updatedQuestionAndAnswerData = mapByIndex.getValue();
            updatedQuestionAndAnswerData.setAnswer(libraryInfo.toString());
            messageData.replace(mapByIndex.getKey(), updatedQuestionAndAnswerData);

            populateListView();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Select item form the list view please").show();
        }


    }

    private Map.Entry<Message, QuestionAndAnswerData> getPairFromLinkedHashMapByIndex(int listIndex) {
        int index = 0;
        for (Map.Entry<Message, QuestionAndAnswerData> entry : messageData.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            if (index == listIndex) {
                return entry;
            }
            index++;
        }
        return null;
    }


    private void populateListView() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messagesListView.getItems().clear();
                for (Map.Entry<Message, QuestionAndAnswerData> entry : messageData.entrySet()) {
                    messagesListView.getItems().add(entry.getValue().toString());
                }
            }
        });
    }


    private void listenForQuestions() {
        Connection connection; // to connect to the JMS
        Session session; // session for creating consumers
        Destination receiveDestination;    //        reference to a queue/topic destination
        MessageConsumer consumer; // for receiving messages
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props.put(("queue.libraryRequestQueue"), " libraryRequestQueue");
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup("libraryRequestQueue");
            consumer = session.createConsumer(receiveDestination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {

                    String requestorQuestion = null;
                    try {

                        // Get the actual message text
                        requestorQuestion = ((ActiveMQTextMessage) msg).getText();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    QuestionAndAnswerData qAndAData = new QuestionAndAnswerData(requestorQuestion);

                    // Fill the map with the new data
                    messageData.put(msg, qAndAData);

                    populateListView();

                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

}
