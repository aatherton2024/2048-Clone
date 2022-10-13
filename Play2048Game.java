import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Scanner;

/*
* Game class for implementation of 2048 clone
*/
public class Play2048Game {

  /*
   * game board
   */
  private Play2048Board game;

  /*
   * Constructor begins game and opens it up for play
   */
  public Play2048Game() {
    game = new Play2048Board(4);
    while (!game.gameOver()) {
      System.out.println("enter a move");
      letsPlay();
    }
  }

  /*
   * Method to read in user key inputs
   */
  public void letsPlay() {
    Scanner sc = new Scanner(System.in);
    char ch = sc.next().charAt(0);
    if (ch == 'w') {
      game.up();
    } else if (ch == 'a') {
      game.left();
    } else if (ch == 's') {
      game.down();
    } else if (ch == 'd') {
      game.right();
    }
  }

  /*
   * Method to print basic instructions
   */
  public static void printRules() {
    System.out.println("Welcome to my 2048 implementation");
    System.out.println("This version uses scanner for input");
    System.out.println("w is up, s is down, a is left, d is right");
  }

  /*
   * Main method used to play 2048
   */
  public static void main(String[] args) {
    printRules();
    Play2048Game g = new Play2048Game();
  }
}
