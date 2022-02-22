public class Question {
    private String question;
    private boolean answer;

    public Question(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public boolean getAnwer() {
        return answer;
    }

    public boolean isCorrect(boolean userAns) {
        return answer == userAns;
    }
}
