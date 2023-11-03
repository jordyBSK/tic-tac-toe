import java.util.Scanner;

public class javaGame {
  static String[] board = new String[9]; // Initialize the game board
  static String winner = ""; // Initialize the winner variable
  static String player = ""; // Initialize the current player variable

  static void checkWinner() {
    for (int a = 0; a < 8; a++) { // Loop to check all possible combinations
      String line = switch (a) {
        case 0 -> board[0] + board[1] + board[2];
        case 1 -> board[3] + board[4] + board[5];
        case 2 -> board[6] + board[7] + board[8];
        case 3 -> board[0] + board[3] + board[6];
        case 4 -> board[1] + board[4] + board[7];
        case 5 -> board[2] + board[5] + board[8];
        case 6 -> board[0] + board[4] + board[8];
        case 7 -> board[2] + board[4] + board[6];
        default -> null;
      };

      if (winner.equalsIgnoreCase("draw")) { // If it's a draw
        System.out.println("It's a draw!");
      }
      // For X winner
      else if (line.equals("XXX")) {
        winner = player;
      }

      // For O winner
      else if (line.equals("OOO")) {
        winner = player;
      }
    }
  }

  static void printBoard() {
    System.out.println("|---|---|---|");
    for (int i = 0; i < 9; i += 3) {
      System.out.println("| " + board[i] + " | " + board[i + 1] + " | " + board[i + 2] + " |");
      System.out.println("|---|---|---|");
    }
  }

  public static void main(String[] args) {
    var turn = 0;

    Scanner sc = new Scanner(System.in);

    for (int a = 0; a < 9; a++) {
      board[a] = String.valueOf(" "); // Initialize the board with spaces
    }

    System.out.println("Welcome to 3x3 Tic Tac Toe.");

    while (winner.equals("" +
            "")) { // Main game loop
      System.out.println("Choose a number:");
      printBoard();
      if (turn % 2 == 0) {
        player = "X"; // X player's turn
      } else {
        player = "O"; // O player's turn
      }
      String choice = sc.nextLine();

      // Check if the input string is empty or contains only spaces
      if (choice.trim().isEmpty()) {
        System.out.println("Please enter a number.");
        continue;
      }

      try {
        int choix = Integer.parseInt(choice);
        if (choix >= 1 && choix <= 9) {
          if (board[choix - 1].equals(" ")) {
            board[choix - 1] = player; // Place the player's symbol on the board
            turn++;
            checkWinner(); // Check if there's a winner
          } else {
            System.out.println("That spot is already taken. Choose a different one.");
          }
        } else {
          System.out.println("Choose a valid number (1 to 9).");
        }
      } catch (NumberFormatException e) {
        System.out.println("Please enter a valid number.");
      }
      if (turn == 9 && winner.isEmpty()) {
        winner = "draw"; // If all turns have been played without a winner, it's a draw
      }
    }

    printBoard();
    if (winner.equals("draw")) {
      System.out.println("It's a draw!");
    } else {
      System.out.println("The winner is " + winner);
    }
  }
}