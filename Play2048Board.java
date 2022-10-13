import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Scanner;

/*
 * Implementation of the 2048 game board. Class includes methods for shifting
 * as well as basic implementations of the extension of Swing's JFrame
 */
public class Play2048Board extends JFrame {

  /*
   * Dimension for the nxn board
   */
  private int boardSize;

  /*
   * Visual representation of the board
   */
  private JLabel[][] table;

  /*
   * Internal representation of the board
   */
  private int[][] board;

  /*
   * Game score
   */
  private int score = 0;

  /*
   * Largest tile
   */
  private int largestTile = 0;

  /*
   * Used for vertical and horizontal slide methods
   */
  private int borderIdx;

  /*
   * Constructor begins game spawning two random tiles
   */
  public Play2048Board(int n) {
    //call JFrame constructor to initialize GUI
    super("Let's play 2048");

    //initilize instance variables and solve problem
    boardSize = n;
    board = new int[boardSize][boardSize];
    initializeBoard();
    createGrid();
    spawn();
    spawn();
  }

  /*
   * Initialize JFrame
   */
  public void initializeBoard() {
    this.setVisible(true);
    this.setLayout(new GridLayout(boardSize, boardSize));
    this.setSize(400, 400); //values can be changed
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  /*
   * Method to create visual representation of board
   */
   public void createGrid() {
     //create colors to alternate for our chess table look
     Color c2 = new Color(119, 148, 85);

     //initialize visual rep instance variable
     table = new JLabel[boardSize][boardSize];

     //fill in table with JLabels and set their bakcgrounds for visuals
     for (int row = 0; row < table.length; row++) {
       for (int col = 0; col < table[0].length; col++) {
         table[row][col] = new JLabel();
         table[row][col].setHorizontalAlignment(SwingConstants.CENTER);

         table[row][col].setSize(50, 50);
         table[row][col].setBackground(new Color(255,255,255));
         table[row][col].setOpaque(true);

         this.add(table[row][col]);
       }
     }

   }

   /*
    * Method to print board
    */
  public void printBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        System.out.print(board[row][col] + " | ");
      }
      System.out.println();
    }
    System.out.println("Score: " + score);
  }

  /*
   * Returns board
   */
  public int[][] getBoard() {
    return board;
  }

  /*
   * Returns visual board
   */
  public JLabel[][] getGUIBoard() {
    return table;
  }

  /*
   * Returns score
   */
  public int getScore() {
    return score;
  }

  /*
   * Returns highest tile
   */
  public int getHighest() {
    return largestTile;
  }

  /*
   * Spawns in a new random tile
   */
  public void spawn() {
    while (true) {
      int row = (int)(Math.random() * boardSize);
      int col = (int)(Math.random() * boardSize);
      double rand = Math.random();
      if(board[row][col] == 0) {
        if (rand < 0.2) {
          board[row][col] = 4;
          table[row][col].setText("4");
          Color c = getColor(board[row][col]);
          table[row][col].setBackground(c);
          return;
        } else {
          board[row][col] = 2;
          table[row][col].setText("2");
          Color c = getColor(board[row][col]);
          table[row][col].setBackground(c);
          return;
        }
      }
    }
  }

  /*
   * Check if game is won or lost
   */
  public boolean gameOver() {
    if (largestTile == 2048) {
      System.out.println("You won!!");
      System.out.println("Score: " + score);
      return true;
    }
    boolean up = canMoveUp();
    boolean left = canMoveLeft();
    boolean down = canMoveDown();
    boolean right = canMoveRight();

    if (up || left || down || right) return false;

    System.out.println("You lost :(");
    System.out.println("Score: " + score);
    return true;
  }

  /*
   * Helper method to see if up move is valid
   */
  public boolean canMoveUp() {
    for (int col = 0; col < boardSize; col++) {
      for (int row = 1; row < boardSize; row++) {
        int above = board[row - 1][col];
        int current = board[row][col];

        if (current != 0 && above == 0) {
          return true;
        }
        if (current == above && current != 0) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Helper method to see if down move is valid
   */
  public boolean canMoveDown() {
    for (int col = 0; col < boardSize; col++) {
      for (int row = boardSize - 2; row >= 0; row--) {
        int below = board[row + 1][col];
        int current = board[row][col];

        if (current != 0 && below == 0) {
          return true;
        }
        if (current == below && current != 0) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Helper method to see if left move is valid
   */
  public boolean canMoveLeft() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 1; col < boardSize; col++) {
        int left = board[row][col - 1];
        int current = board[row][col];

        if (current != 0 && left == 0) {
          return true;
        }
        if (current == left && current != 0) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Helper method to see if right move is valid
   */
  public boolean canMoveRight() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = boardSize - 2; col >= 0; col--) {
        int right = board[row][col + 1];
        int current = board[row][col];

        if (current != 0 && right == 0) {
          return true;
        }
        if (current == right && current != 0) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * Method to shift board left
   */
  public void left() {
    if (canMoveLeft()) {
      for (int row = 0; row < boardSize; row++) {
        borderIdx = 0;
        for (int col = 0; col < boardSize; col++) {
          if (board[row][col] != 0 && borderIdx <= col) {
            horizontalSlide(row, col, true);
          }
        }
      }
      spawn();
    }
  }

  /*
   * Method to shift board right
   */
  public void right() {
    if (canMoveRight()) {
      for (int row = 0; row < boardSize; row++) {
        borderIdx = boardSize - 1;
        for (int col = boardSize - 1; col >= 0; col--) {
          if (board[row][col] != 0 && borderIdx >= col) {
            horizontalSlide(row, col, false);
          }
        }
      }
      spawn();
    }
  }

  /*
   * Method to shift board up
   */
  public void up() {
    if (canMoveUp()) {
      for (int col = 0; col < 4; col++) {
        int borderIdx = 0;
        for (int row = 0; row < 4; row++) {
          if ((board[row][col]) != 0 && (borderIdx <= row)) {
            verticalSlide(row, col, "up");
          }
        }
      }
      spawn();
    }
  }

  /*
   * Method to shift board down
   */
  public void down() {
    if (canMoveDown()) {
      for (int col = 0; col < boardSize; col++) {
        int borderIdx = 3;
        for (int row = 3; row >= 0; row--) {
          if ((board[row][col]) != 0 && (borderIdx >= row)) {
            verticalSlide(row, col, "down");
          }
        }
      }
      spawn();
    }
  }

  /*
   * Method to implement up and down movement
   */
  public void verticalSlide(int row, int col, String str) {
    if (borderIdx < 0 || borderIdx >= 4) return;
    JLabel initial = table[borderIdx][col];
    JLabel comp = table[row][col];

    int initialVal = board[borderIdx][col];
    int compVal = board[row][col];

    if (initialVal == 0 || initialVal == compVal) {
      if (row > borderIdx || (str.equals("down") && (row < borderIdx))) {
        int scoreAddition = initialVal + compVal;
        if (initialVal != 0) {
          score += scoreAddition;
        }
        board[borderIdx][col] = scoreAddition;
        initial.setText("" + scoreAddition);
        Color c = getColor(scoreAddition);
        initial.setBackground(c);

        board[row][col] = 0;
        comp.setText("");
        Color c2 = getColor(0);
        table[row][col].setBackground(c2);
      }
    } else {
      if (str.equals("down")) {
        borderIdx--;
      } else {
        borderIdx++;
      }
      verticalSlide(row, col, str);
    }
  }

  /*
   * Method to implement right and left movement
   */
  public void horizontalSlide(int row, int col, boolean moveLeft) {
    JLabel initial = table[row][borderIdx];
    JLabel comp = table[row][col];

    int initialVal = board[row][borderIdx];
    int compVal = board[row][col];

    if (initialVal == 0 || initialVal == compVal) {
      if (col > borderIdx || (!moveLeft && col < borderIdx)) {
        int scoreAddition = initialVal + compVal;
        if (initialVal != 0) {
          score += scoreAddition;
        }
        board[row][borderIdx] = scoreAddition;
        initial.setText("" + scoreAddition);
        Color c = getColor(scoreAddition);
        initial.setBackground(c);

        board[row][col] = 0;
        comp.setText("");
        Color c2 = getColor(0);
        table[row][col].setBackground(c2);
      }
    } else {
      if (!moveLeft) {
        borderIdx--;
      } else {
        borderIdx++;
      }
      horizontalSlide(row, col, moveLeft);
    }
  }

  /*
   * Method to get background color for tile
   */
  public Color getColor(int v) {
    Color tileColor;
    if (v == 2) {
        tileColor = new Color(238, 228, 218);
    } else if (v == 4) {
      tileColor = new Color( 237, 224, 200 );
    } else if (v == 8) {
      tileColor = new Color( 242, 177, 121 );
    } else if (v == 16) {
      tileColor = new Color( 245, 149, 99 );
    } else if (v == 32) {
      tileColor = new Color( 246, 124, 95 );
    } else if (v == 64) {
      tileColor = new Color( 246, 94, 59 );
    } else if (v == 128) {
      tileColor = new Color( 237, 207, 114 );
    } else if (v == 256) {
      tileColor = new Color( 237, 204, 97 );
    } else if (v == 512) {
      tileColor = new Color( 237, 200, 80 );
    } else if (v == 1024) {
      tileColor = new Color( 237, 197, 63 );
    } else if (v == 2048) {
      tileColor = new Color( 237, 194, 46 );
    } else {
      tileColor = new Color(255, 255, 255);
    }
    return tileColor;
  }

  /*
   * Main method used for testing purposes
   */
  public static void main(String[] args) {
    Play2048Board p = new Play2048Board(4);
  }
}
