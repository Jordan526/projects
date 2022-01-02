import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameMain {

    //setting up global variables so I have access to them throughout the code
    private static int deck_size = 52;
    private static final int DECK_TOTAL = 52;
    private static String guess;
    private static int userScore;
    private static int computerScore;
    private static LinkedList<String> computerHand = new LinkedList<>();
    private static LinkedList<String> userHand = new LinkedList<>();

    //intro method that introduces you to the game and tells you the rules/gist of the game
    public static void intro() throws InterruptedException {
        System.out.println("Hello!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Welcome to the game: 4 Of A Kind!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("The goal of this game is to collect the most 4 of a kinds out of a 52 card " + "\"deck\"");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Here are the rules!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Each person is first dealt 5 cards, ranking 1 - 14 with 11 for Aces, 12 for Jack's, 13 for Queen's, and 14 for King's");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("You then guess 1 card from your opponent's hand, if you get a correct match you take the card from your opponent's hand, add it to your hand then guess another card");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("If your guess is wrong, you draw one card from the top of the deck and the opponent goes");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("If the card you drew is the card you initially guessed, you do NOT get to guess again");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Simply collect more 4 of a kinds than your opponent");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Are you ready?");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("Let's begin!");
        System.out.println();
        TimeUnit.SECONDS.sleep(2);
    }

    //method that deals 5 random cards to the users hand from 1 - 15 to start the game
    //the method also decreases the deck size as all 5 cards are dealt from the deck
    public static LinkedList<String> dealCardsToUser() {
        String[] cardValues = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomCard = random.nextInt(cardValues.length);
            userHand.add(cardValues[randomCard]);
            deck_size--;
        }
        return userHand;
    }

    //method that deals 5 random cards to the computers hand from 1 - 15 to start the game
    //the method also decreases the deck size as all 5 cards are dealt from the deck
    public static LinkedList<String> dealCardsToComputer() {
        String[] cardValues = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomCard = random.nextInt(cardValues.length);
            computerHand.add(cardValues[randomCard]);
            deck_size--;
        }
        return computerHand;
    }


    //method that deals an n amount of cards to the user (card chosen randomly)
    //method also decreases deck size for each nth card dealt
    //method also displays what card was dealt
    //method lastly checks whether the card dealt gives the user a 4 of a kind
    public static void dealCards(LinkedList<String> user, int cardAmount) {
        String[] cardValues = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        Random random = new Random();
        int randomCard = 0;
        for (int i = 0; i < cardAmount; i++) {
            randomCard = random.nextInt(cardValues.length) + 1;
        }
        deck_size--;
        user.add(String.valueOf(randomCard));
        System.out.println("You drew a " + randomCard);
        computerCheckFor4(String.valueOf(randomCard));
        userCheckFor4(String.valueOf(randomCard));
    }

    //method that deals an n amount of cards to the user (chosen randomly)
    //method also decreases deck size for each nth card dealt
    //method also displays what card was dealt
    //method lastly checks whether the card dealt gives the user a 4 of a kind
    public static void dealCardToComputer(LinkedList<String> user, int cardAmount) {
        String[] cardValues = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        Random random = new Random();
        int randomCard = 0;
        for (int i = 0; i < cardAmount; i++) {
            randomCard = random.nextInt(cardValues.length) + 1;
        }
        deck_size--;
        user.add(String.valueOf(randomCard));
        System.out.println("The computer drew a card");
        computerCheckFor4(String.valueOf(randomCard));
        userCheckFor4(String.valueOf(randomCard));
    }

    //method that checks the users hand for a 4 of a kind
    //if the user has a 4 of a kind, user score increases by 1 and all 4 cards are removed from the hand
    //if your hand is empty after removing all 4 cards, 5 new ones are dealt to you
    public static void userCheckFor4(String cardGuess) {
        int maxCount = 4;
        int count = 0;
        for (int i = 0; i < userHand.size(); i++) {
            if (userHand.get(i).equals(cardGuess)) {
                count++;
                if (count == maxCount) {
                    System.out.println("It looks like you have a 4 of a kind! You get 1 point");
                    userScore++;
                    for (int j = 0; j < maxCount; j++) {
                        userHand.remove(cardGuess);
                        if (userHand.isEmpty()) {
                            dealCards(userHand, 5);
                        }
                    }
                }
            }
        }
    }

    //method that checks the computers hand for a 4 of a kind
    //if the computer has a 4 of a kind, computer score increases by 1 and all 4 cards are removed from the hand
    //if the hand is empty after removing all 4 cards, 5 new ones are dealt to the computer
    public static void computerCheckFor4(String cardGuess) {
        int maxCount = 4;
        int count = 0;
        for (int i = 0; i < computerHand.size(); i++) {
            if (computerHand.get(i).equals(cardGuess)) {
                count++;
                if (count == maxCount) {
                    System.out.println("It looks like you have a 4 of a kind! You get 1 point");
                    computerScore++;
                    for (int j = 0; j < maxCount; j++) {
                        computerHand.remove(cardGuess);
                        if (computerHand.isEmpty()) {
                            dealCards(computerHand, 5);
                        }
                    }
                }
            }
        }
    }


    //method that breaks down the user moves
    //first asks what card you want to guess, checks if the card guessed gives you a four of a kind, then checks if the card you guessed is in your hand
    //if the card is not a valid guess, you will be prompted to enter a valid guess (the card guessed has to be in the hand)
    //if the card guessed is a match, you will be prompted to guess again until your guess is wrong
    //if you run out of cards while getting a four of a kind, 5 new cards are dealt to you
    //if the guess is wrong, you draw a card from the deck
    public static void userMove() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What card would you like to guess from your opponents hand? ");
        System.out.print("(Your hand " + userHand + "): ");
        guess = userInput.next();
        userCheckFor4(guess);
        while (!userHand.contains(guess)) {
            System.out.print("Looks like you do not have that card in your hand, please choose a card that is in your hand!: ");
            guess = userInput.next();
        }
        while (computerHand.contains(guess)) {
            System.out.println("Looks like a match!");
            userHand.add(guess);
            computerHand.remove(guess);
            if (computerHand.isEmpty()) {
                dealCardsToComputer();
            }
            userCheckFor4(guess);
            System.out.print("make another guess: ");
            guess = userInput.next();
            System.out.println();
            while (!userHand.contains(guess)) {
                System.out.print("Looks like you do not have that card in your hand, please choose a card that is in your hand!: ");
                guess = userInput.next();
                System.out.println();
            }
        }
        System.out.println("No match! So, draw a card");
        dealCards(userHand, 1);
        System.out.println("user hand " + userHand);
        System.out.println("(deck count is currently at " + deck_size + ")");
        System.out.println();
    }

    //method that sets up the computer's moves
    //a random object is initialized to allow the computer to randomly guess "cards" from their hand
    //if the computer correctly guesses a card, it is prompted to continue guessing until the guess is wrong
    //the method also checks if the computer has a 4 of a kind after each guess
    public static void computerMove() {
        Random random = new Random();
        int computerGuess = random.nextInt(computerHand.size());
        System.out.println("The computer guessed " + computerHand.get(computerGuess));
        computerCheckFor4(guess);
        while (userHand.contains(computerHand.get(computerGuess))) {
            System.out.println("looks like a match!");
            computerHand.add(computerHand.get(computerGuess));
            userHand.remove(computerHand.get(computerGuess));
            if (userHand.isEmpty()) {
                dealCardsToUser();
            }
            System.out.println("make another guess: ");
            System.out.println();
            computerGuess = random.nextInt(computerHand.size());
            System.out.println("The computer guessed " + computerHand.get(computerGuess));
            computerCheckFor4(guess);
        }
        System.out.println("No match! So, draw a card");
        dealCardToComputer(computerHand, 1);
        System.out.println("(deck count is currently at " + deck_size + ")");
        System.out.println();
    }

    //method that sets up the game and deals the starting 5 cards to you and the opponent
    //a do while loop is used so each player can continue to have a turn until the deck size reaches 0
    //after the game, the score is printed
    public static void play4OfAKind() {
        System.out.println("Here are your cards: " + dealCardsToUser());
        dealCardsToComputer();
        System.out.println();
        do {
            userMove();
            computerMove();
        } while (deck_size > 0);
        System.out.println("Looks like the game has finished since there are no more cards left in the deck! ");
        System.out.println("The scores are: user score = " + userScore + " computer score = " + computerScore);
    }


    //main method that sets up a scanner and borrowed Exterminator class from Jasper dating back to our group project from CS 141
    //a boolean variable is established to keep track if the user wants to continue playing
    //do while loop is used to continue to play games if the user wants to
    //if the user wants to play a new game, all hands are wiped, deck size is restored to 52, each player is dealt 5 new cards and the game starts over
    //the scores for each user do not reset after each game, giving a running total at the end when the user decides to stop playing after 1 or n amount of games
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        ValidMove e = new ValidMove();
        boolean isPlaying;
        intro();
        do {
            play4OfAKind();
            char command = e.scanForYN(input, "Would you like to play again? Y for yes, N for no and to exit: ");
            System.out.println();
            if (command == 'Y') {
                userHand.clear();
                computerHand.clear();
                deck_size = DECK_TOTAL;
                isPlaying = true;
            } else {
                System.out.println("Goodbye!");
                isPlaying = false;
            }
        } while (isPlaying == true);
    }
}
