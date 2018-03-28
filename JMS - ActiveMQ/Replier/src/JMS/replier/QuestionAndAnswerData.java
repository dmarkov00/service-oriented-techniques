package JMS.replier;

public class QuestionAndAnswerData {
    public QuestionAndAnswerData(String question) {
        this.question = question;
    }

    private String question;
    private String answer;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public String toString() {
        if (answer == null) {
            return question + " ---> " + "waiting for a reply";
        } else {
            return question + " ---> " + answer;
        }
    }
}
