import java.util.*;

public class Main {

    public static char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

    public static ArrayList<Integer> playerPositions = new ArrayList<>();
    public static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {

        printGameBoard(gameBoard);
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter a number (1 - 9): ");

            int playerPosition = input.nextInt();
            while(playerPositions.contains(playerPosition) || computerPositions.contains(playerPosition)) {
                System.out.println("Position already taken! Please enter a position that has not been taken!");
                playerPosition = input.nextInt();
            }
            placePiece(gameBoard, playerPosition, "user");
            String winner = checkWinner();
            if(winner.length() > 0) {
                System.out.println(winner);
                break;
            }

            Random randomNumber = new Random();
            int computerPosition = randomNumber.nextInt(9) + 1;
            while(playerPositions.contains(computerPosition)|| computerPositions.contains(computerPosition)) {
                computerPosition =  randomNumber.nextInt(9) + 1;
            }
            placePiece(gameBoard, computerPosition, "computer");
            winner = checkWinner();
            if(winner.length() > 0) {
                System.out.println(winner);
                break;
            }
        }while(true);



    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char character : row) {
                System.out.print(character);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int placement, String user) {
        char symbol = ' ';

        if (user.equals("user")) {
            symbol = 'X';
            playerPositions.add(placement);
        } else if (user.equals("computer")) {
            computerPositions.add(placement);
            symbol = 'O';
        }

        if (placement == 1) {
            gameBoard[0][0] = symbol;
        } else if (placement == 2) {
            gameBoard[0][2] = symbol;
        } else if (placement == 3) {
            gameBoard[0][4] = symbol;
        } else if (placement == 4) {
            gameBoard[2][0] = symbol;
        } else if (placement == 5) {
            gameBoard[2][2] = symbol;
        } else if (placement == 6) {
            gameBoard[2][4] = symbol;
        } else if (placement == 7) {
            gameBoard[4][0] = symbol;
        } else if (placement == 8) {
            gameBoard[4][2] = symbol;
        } else if (placement == 9) {
            gameBoard[4][4] = symbol;
        }
        printGameBoard(gameBoard);
        System.out.println();
    }

    public static  String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftCollum = Arrays.asList(1, 4, 7);
        List middleCollum = Arrays.asList(2, 5, 8);
        List rightCollum = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winningConditions = new ArrayList<>();
        winningConditions.add(topRow);
        winningConditions.add(bottomRow);
        winningConditions.add(middleRow);
        winningConditions.add(leftCollum);
        winningConditions.add(rightCollum);
        winningConditions.add(middleCollum);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List winning : winningConditions) {
            if (playerPositions.containsAll(winning)) {
                return "Congrats! You've won!";
            } else if (computerPositions.containsAll(winning)) {
                return "The Computer has won! You lose!";
            }
            if (playerPositions.size() + computerPositions.size() == 9) {
                return "Its a tie!";
            }
        }
        return "";
    }
}
