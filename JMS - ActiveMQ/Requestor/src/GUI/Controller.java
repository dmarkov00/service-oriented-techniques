package GUI;

import JMS.requestor.LibraryInfo;
import JMS.requestor.QuestionAndAnswerData;
import JMS.requestor.ReplyQueue;
import JMS.requestor.Requestor;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;


public class Controller {
    @FXML
    public ListView<String> messagesListView;
    @FXML
    public TextField messageField;

    private Map<String, QuestionAndAnswerData> messageData;

    private Requestor requestor = new Requestor();

    @FXML
    public void buttonSendClicker() {
        // Get field text
        String messageBody = messageField.getText();

        // Send new request
        requestor.sendRequest(messageBody);

        // Retrieve the latest values from the hash map
        messageData = requestor.messageData;

        // Update the list view
        this.populateListView();

        messageField.setText("");
    }

    @FXML
    public void initialize() {
        this.listenForAnswers();

    }


    private void listenForAnswers() {

        Connection connection; // to connect to the JMS
        Session session; // session for creating consumers
        Destination receiveDestination;    //        reference to a queue/topic destination
        MessageConsumer consumer; // for receiving messages
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

            props.put(("queue." + ReplyQueue.replyQueueName), ReplyQueue.replyQueueName);
            Context jndiContext = new InitialContext(props);
            ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext
                    .lookup("ConnectionFactory");
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // Connect to the receiver destination
            receiveDestination = (Destination) jndiContext.lookup(ReplyQueue.replyQueueName);
            consumer = session.createConsumer(receiveDestination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message msg) {

                    String replierAnswer = null;
                    String correlationId = null;
                    try {
                        // Retrieve correlation id
                        correlationId = msg.getJMSCorrelationID();
                        // Find the question that needs to be answered
                        QuestionAndAnswerData questionAndAnswerData = findQuestionByCorrelationID(correlationId);
                        // Get the actual message
                        replierAnswer = ((ActiveMQTextMessage) msg).getText();

                        // Try to deserialize if library info was sent back
                        Gson gson = new Gson();

                        // If the object can not be parsed exception is thrown, that means we are not receiving library info from the replier
                        try {
                            LibraryInfo libraryInfo = gson.fromJson(replierAnswer, LibraryInfo.class);
                            replierAnswer = libraryInfo.toString();

                        } catch (Exception e) {
//                            System.out.println(e);
                        }


                        // Set answer in the Q&AData object
                        questionAndAnswerData.setAnswer(replierAnswer);
                        //Update the hash map with the new answer value
                        requestor.messageData.replace(correlationId, questionAndAnswerData);


                        populateListView();

                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            connection.start(); // this is needed to start receiving messages
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    private void populateListView() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                messagesListView.getItems().clear();
                for (Map.Entry<String, QuestionAndAnswerData> entry : messageData.entrySet()) {
                    messagesListView.getItems().add(entry.getValue().toString());
                }

            }
        });


    }

    private QuestionAndAnswerData findQuestionByCorrelationID(String correlationID) {
        for (Map.Entry<String, QuestionAndAnswerData> entry : messageData.entrySet()) {
            if (entry.getKey().equals(correlationID)) {
                return entry.getValue();
            }
        }
        return null;
    }


}
