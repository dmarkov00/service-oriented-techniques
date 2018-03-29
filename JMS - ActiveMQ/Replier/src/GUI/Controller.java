package GUI;


import JMS.replier.QuestionAndAnswerData;
import JMS.replier.Replier;
import javafx.fxml.FXML;
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
    private Destination replyAddress;

    private Map<String, QuestionAndAnswerData> messageData = new LinkedHashMap<>();

    public void buttonSendClicker() {
        String messageBody = messageField.getText();

        // Retrieve the index of the selected item from the list view
        int selectedItemIndex = messagesListView.getSelectionModel().getSelectedIndex();
        // Get the map value for this index (the map values are inserted the same way as the list view values, so the indices match)
        Map.Entry<String, QuestionAndAnswerData> mapByIndex = this.getFromLinkedHashMapByIndex(selectedItemIndex);

        // Get the id, for setting as a correlation id, later on
        String requestorMessageId = mapByIndex.getKey();

        // Send reply
        replier.sendReply(messageBody, requestorMessageId, replyAddress);

        // Updating the map value with the answer string
        QuestionAndAnswerData updatedQuestionAndAnswerData = mapByIndex.getValue();
        updatedQuestionAndAnswerData.setAnswer(messageBody);
        messageData.replace(requestorMessageId, updatedQuestionAndAnswerData);

        populateListView();

        messageField.setText("");

    }

    private Map.Entry<String, QuestionAndAnswerData> getFromLinkedHashMapByIndex(int listIndex) {
        int index = 0;
        for (Map.Entry<String, QuestionAndAnswerData> entry : messageData.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
            if (index == listIndex) {
                return entry;
            }
            index++;
        }
        return null;
    }


    @FXML
    public void initialize() {
        this.listenForQuestions();

    }

    private void populateListView() {
        messagesListView.getItems().clear();
        for (Map.Entry<String, QuestionAndAnswerData> entry : messageData.entrySet()) {
            messagesListView.getItems().add(entry.getValue().toString());
        }
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
                    String messageId = null;
                    String requestorQuestion = null;
                    try {
                        // Retrieve message id
                        messageId = msg.getJMSMessageID();
                        // Get the actual message
                        requestorQuestion = ((ActiveMQTextMessage) msg).getText();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    QuestionAndAnswerData qAndAData = new QuestionAndAnswerData(requestorQuestion);

                    try {
                        replyAddress = msg.getJMSReplyTo();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }

                    // Fill the map for later reference
                    messageData.put(messageId, qAndAData);

                    // Add the question to the list view
//                    messagesListView.getItems().add(qAndAData.toString());

                    populateListView();


                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }
}
