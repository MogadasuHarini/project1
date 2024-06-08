import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class User {
    private String name;
    private int totalScore;
    private int quizCount;

    public User(String name) {
        this.name = name;
        this.totalScore = 0;
        this.quizCount = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public double getAverageScore() {
        if (quizCount == 0) return 0;
        return (double) totalScore / quizCount;
    }

    public void addScore(int score) {
        this.totalScore += score;
        this.quizCount++;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Total Score: " + totalScore + ", Average Score: " + getAverageScore();
    }
}

class Leaderboard {
    private List<User> users;

    public Leaderboard() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addScore(String userName, int score) {
        for (User user : users) {
            if (user.getName().equals(userName)) {
                user.addScore(score);
                return;
            }
        }
        System.out.println("User not found: " + userName);
    }

    public void displayLeaderboard() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.getTotalScore(), u1.getTotalScore());
            }
        });

        System.out.println("Leaderboard (Total Score):");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public void displayAverageLeaderboard() {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Double.compare(u2.getAverageScore(), u1.getAverageScore());
            }
        });

        System.out.println("Leaderboard (Average Score):");
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        leaderboard.addUser(user1);
        leaderboard.addUser(user2);
        leaderboard.addUser(user3);

        leaderboard.addScore("Alice", 50);
        leaderboard.addScore("Bob", 30);
        leaderboard.addScore("Charlie", 40);
        leaderboard.addScore("Alice", 70);
        leaderboard.addScore("Bob", 60);
        leaderboard.addScore("Charlie", 90);

        leaderboard.displayLeaderboard();
        leaderboard.displayAverageLeaderboard();
    }
}
