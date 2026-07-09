
import java.util.Random;
import java.util.Scanner;

class GuessNumberGame {
    private int secretNumber;
    private int maxNumber;
    private int attemptsLimit;
    private int attemptsUsed;
    private int score;
    private static int highScore = 0;

    private Scanner sc;
    private Random random;

    public GuessNumberGame() {
        sc = new Scanner(System.in);
        random = new Random();
    }

    public void startGame() {
        boolean playAgain;

        System.out.println("==================================");
        System.out.println("     ADVANCED GUESS NUMBER GAME");
        System.out.println("==================================");

        do {
            chooseDifficulty();
            generateNumber();
            playRound();
            updateHighScore();

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();

            playAgain = choice.equals("yes") || choice.equals("y");

        } while (playAgain);

        System.out.println("\nThank you for playing!");
        System.out.println("Final High Score: " + highScore);
        sc.close();
    }

    private void chooseDifficulty() {
        System.out.println("\nChoose Difficulty Level:");
        System.out.println("1. Easy   - Guess 1 to 50  | 10 attempts");
        System.out.println("2. Medium - Guess 1 to 100 | 7 attempts");
        System.out.println("3. Hard   - Guess 1 to 200 | 5 attempts");

        int choice;

        while (true) {
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();

                if (choice == 1) {
                    maxNumber = 50;
                    attemptsLimit = 10;
                    score = 100;
                    break;
                } else if (choice == 2) {
                    maxNumber = 100;
                    attemptsLimit = 7;
                    score = 150;
                    break;
                } else if (choice == 3) {
                    maxNumber = 200;
                    attemptsLimit = 5;
                    score = 200;
                    break;
                } else {
                    System.out.println("Invalid choice! Choose 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input! Enter only numbers.");
                sc.next();
            }
        }
    }

    private void generateNumber() {
        secretNumber = random.nextInt(maxNumber) + 1;
        attemptsUsed = 0;
    }

    private void playRound() {
        System.out.println("\nI have selected a number between 1 and " + maxNumber + ".");
        System.out.println("You have " + attemptsLimit + " attempts.");
        System.out.println("Try to guess it!");

        while (attemptsUsed < attemptsLimit) {
            int guess = getValidGuess();
            attemptsUsed++;

            if (guess == secretNumber) {
                System.out.println("\nCongratulations! You guessed correctly!");
                System.out.println("Attempts Used: " + attemptsUsed);

                int finalScore = score - ((attemptsUsed - 1) * 10);

                if (finalScore < 0) {
                    finalScore = 0;
                }

                score = finalScore;
                System.out.println("Your Score: " + score);
                return;
            } else if (guess < secretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }

            giveHint(guess);

            int remainingAttempts = attemptsLimit - attemptsUsed;
            System.out.println("Attempts remaining: " + remainingAttempts);
        }

        score = 0;
        System.out.println("\nGame Over!");
        System.out.println("The correct number was: " + secretNumber);
        System.out.println("Your Score: " + score);
    }

    private int getValidGuess() {
        int guess;

        while (true) {
            System.out.print("\nEnter your guess: ");

            if (sc.hasNextInt()) {
                guess = sc.nextInt();

                if (guess >= 1 && guess <= maxNumber) {
                    return guess;
                } else {
                    System.out.println("Guess must be between 1 and " + maxNumber + ".");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
            }
        }
    }

    private void giveHint(int guess) {
        int difference = Math.abs(secretNumber - guess);

        if (difference <= 5) {
            System.out.println("Hint: Very close!");
        } else if (difference <= 15) {
            System.out.println("Hint: Close!");
        } else {
            System.out.println("Hint: Far away!");
        }

        if (secretNumber % 2 == 0) {
            System.out.println("Extra Hint: The number is even.");
        } else {
            System.out.println("Extra Hint: The number is odd.");
        }
    }

    private void updateHighScore() {
        if (score > highScore) {
            highScore = score;
            System.out.println("New High Score: " + highScore);
        } else {
            System.out.println("High Score: " + highScore);
        }
    }
}