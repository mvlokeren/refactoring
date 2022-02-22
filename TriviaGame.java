import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Holds all the information and logic for a True/False Trivia Game
 */
class TriviaGame {
    private static final String FILE_NAME = "questions.txt";
    private static TriviaGame game;
    private ArrayList<Question> questions;
    private Random rand;
    Scanner in;
    private int userScore = 0;
    private int compScore = 0;

    /**
     * Creates a new Trivia Game
     * Loads all the question Data
     */
    private TriviaGame() {
        rand = new Random();
        questions = loadQuestions();
    }

    public static TriviaGame getInstance() {
        if (game == null) {
            game = new TriviaGame();
        }

        return game;
    }

    private ArrayList<Question> loadQuestions() {
        questions = new ArrayList<Question>();

        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);

            // loop through to get each question
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                String question = data[0].trim();
                String answer = data[1].trim().toLowerCase();
                questions.add(new Question(question, getBool(answer)));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, we could not properly read the questions file");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }

    private boolean getBool(String data) {
        return data.equalsIgnoreCase("true");
    }

    /**
     * Plays the game,
     * Will keep playing as the long as the user indicates they want to
     */
    public void play() {
        System.out.println("Welcome to our Trivia Game");
        in = new Scanner(System.in);

        while (true) {
            if (playRound() == 1) {
                userScore++;
            } else {
                compScore++;
            }

            if (!playAgain()) {
                break;
            }
        }
        displayStats();
        in.close();
    }

    private void displayStats() {
        System.out.println("Goodbye");
        int totalGames = userScore + compScore;
        System.out.println(
                "You got " + userScore + " out of " + totalGames + " questions right.");
        System.out.println(
                "Comp got " + compScore + " out of " + totalGames + " questions right.");
    }

    /**
     * Plays one round of the game, asking and answering a random quesiton
     * Each time a question is presented we ensure the question is not asked again
     * Will increment the score if the user wins.
     */
    private int playRound() {

        int questionNum = rand.nextInt(questions.size());
        Question question = questions.get(questionNum);
        System.out.println(question.getQuestion());
        System.out.print("Enter answer, True or False: ");
        boolean userAns = getBool(in.nextLine().trim().toLowerCase());
        questions.remove(questionNum);

        if (question.isCorrect(userAns)) {
            System.out.println("Yay you got it!");
            return 1;
        } else {
            System.out.println("Sorry that was incorrect.");
            return -1;
        }
    }

    private boolean playAgain() {

        if (questions.isEmpty()) {
            System.out.println("Game Over! You answered all the questions");
            return false;
        }

        System.out.print("Do you want to play again, (Y)es or (N)o: ");
        String contInput = in.nextLine().trim().toLowerCase();

        return contInput.equals("y") || contInput.equals("yes");
    }

    public static void main(String[] args) {
        TriviaGame game = TriviaGame.getInstance();
        game.play();
    }
}