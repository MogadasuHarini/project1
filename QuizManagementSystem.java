import java.util.ArrayList;
import java.util.Scanner;

public class QuizManagementSystem {
    private static ArrayList<Quiz> quizzes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Quiz Management System");
            System.out.println("1. Create a Quiz");
            System.out.println("2. Edit a Quiz");
            System.out.println("3. Delete a Quiz");
            System.out.println("4. List Quizzes");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (option) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    editQuiz();
                    break;
                case 3:
                    deleteQuiz();
                    break;
                case 4:
                    listQuizzes();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void createQuiz() {
        System.out.print("Enter the quiz title: ");
        String title = scanner.nextLine();
        Quiz quiz = new Quiz(title);

        while (true) {
            System.out.print("Enter the question title: ");
            String questionTitle = scanner.nextLine();
            String[] options = new String[4];
            for (int i = 0; i < 4; i++) {
                System.out.print("Enter option " + (i + 1) + ": ");
                options[i] = scanner.nextLine();
            }
            System.out.print("Enter the number of the correct option (1-4): ");
            int correctOption = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            Question question = new Question(questionTitle, options, correctOption);
            quiz.addQuestion(question);

            System.out.print("Do you want to add another question? (yes/no): ");
            String addAnother = scanner.nextLine();
            if (!addAnother.equalsIgnoreCase("yes")) {
                break;
            }
        }

        quizzes.add(quiz);
        System.out.println("Quiz created successfully!");
    }

    private static void editQuiz() {
        listQuizzes();
        System.out.print("Enter the number of the quiz you want to edit: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline left-over

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz number. Please try again.");
            return;
        }

        Quiz quiz = quizzes.get(quizIndex);
        System.out.print("Enter the new title for the quiz (or press Enter to keep current title): ");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            quiz.setTitle(newTitle);
        }

        while (true) {
            quiz.listQuestions();
            System.out.print("Enter the number of the question you want to edit (or 0 to finish editing): ");
            int questionIndex = scanner.nextInt() - 1;
            scanner.nextLine();  // Consume newline left-over

            if (questionIndex == -1) {
                break;
            }

            if (questionIndex < 0 || questionIndex >= quiz.getQuestions().size()) {
                System.out.println("Invalid question number. Please try again.");
                continue;
            }

            Question question = quiz.getQuestions().get(questionIndex);
            System.out.print("Enter the new title for the question (or press Enter to keep current title): ");
            String newQuestionTitle = scanner.nextLine();
            if (!newQuestionTitle.isEmpty()) {
                question.setTitle(newQuestionTitle);
            }

            for (int i = 0; i < 4; i++) {
                System.out.print("Enter new option " + (i + 1) + " (or press Enter to keep current option): ");
                String newOption = scanner.nextLine();
                if (!newOption.isEmpty()) {
                    question.getOptions()[i] = newOption;
                }
            }

            System.out.print("Enter the number of the new correct option (or 0 to keep current correct option): ");
            int newCorrectOption = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over
            if (newCorrectOption != 0) {
                question.setCorrectOption(newCorrectOption);
            }
        }

        System.out.println("Quiz edited successfully!");
    }

    private static void deleteQuiz() {
        listQuizzes();
        System.out.print("Enter the number of the quiz you want to delete: ");
        int quizIndex = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume newline left-over

        if (quizIndex < 0 || quizIndex >= quizzes.size()) {
            System.out.println("Invalid quiz number. Please try again.");
            return;
        }

        quizzes.remove(quizIndex);
        System.out.println("Quiz deleted successfully!");
    }

    private static void listQuizzes() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getTitle());
        }
    }
}

class Quiz {
    private String title;
    private ArrayList<Question> questions;

    public Quiz(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void listQuestions() {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getTitle());
        }
    }
}

class Question {
    private String title;
    private String[] options;
    private int correctOption;

    public Question(String title, String[] options, int correctOption) {
        this.title = title;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(int correctOption) {
        this.correctOption = correctOption;
    }
}
