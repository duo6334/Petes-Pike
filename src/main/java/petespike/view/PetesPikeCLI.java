package petespike.view;

import java.util.List;
import java.util.Scanner;

import petespike.model.Direction;
import petespike.model.Move;
import petespike.model.PetesPike;
import petespike.model.PetesPikeException;
import petespike.model.Position;

public class PetesPikeCLI {
    public static final String HELP = "help";
    public static final String BOARD = "board";
    public static final String RESET = "reset";
    public static final String NEW = "new";
    public static final String MOVE = "move";
    public static final String HINT = "hint";
    public static final String QUIT = "quit";

    private static String fileName;     // current puzzle filename

    /**
     * Display commands
     */

     public static void help(){
        System.out.println("Commands:");
        System.out.println("help - show commands");
        System.out.println("board - display current board");
        System.out.println("reset - reset puzzle");
        System.out.println("new <filename> - start a new puzzle");
        System.out.println("move <row> <col> <direction> - move piece (u, d, l, r)");
        System.out.println("hint - show a valid move");
        System.out.println("quit - exit the game");
     }

    /*
     * Display board & move count
     */

     public static void display(PetesPike game){
       System.out.println(game);
       //get moves count
       System.out.println("Moves: " + game.getMoveCount());
      }

    /**
     * Attempt to make a move.
     */
      public static void move(String[] parts, PetesPike game) throws PetesPikeException {
        try {
            int row = Integer.parseInt(parts[1])-1;
            int col = Integer.parseInt(parts[2])-1;
            Position pos = new Position(row, col);
            String dir = parts[3];
            Direction direction;

            if (dir.equals("u")) {
            direction = Direction.UP;
           } else if (dir.equals("d")) {
            direction = Direction.DOWN;
           } else if (dir.equals("l")) {
            direction = Direction.LEFT;
           } else if (dir.equals("r")) {
            direction = Direction.RIGHT;
           } else {
            System.out.println("Direction must be u, d, l, or r.");
            return;
           }


            Move move = new Move(pos, direction);
            game.makeMove(move);
            display(game);

        } catch (Exception e) {
            System.out.println("Usage: move <row> <col> <direction>");
        }
      }

    /*
     * reset game
     */

      public static void reset() throws PetesPikeException{
        PetesPike newGame = new PetesPike(fileName);
        play(newGame);
      }

    /*
     * start new puzzle
     */

      public static void newGame(){
        try {
            fileName = "";
            PetesPike newGame = new PetesPike(fileName);
            play(newGame);
        } catch (Exception e) {
            System.out.println("Usage: new <filename>");
        }
      }

    /*
     * valid move
     */

      public static void hint(PetesPike game){
        List possibleMoves= game.getPossibleMoves();
        if(possibleMoves.size()==0){
          System.out.println("no moves found");
        }else{
        System.out.println("Try: "+possibleMoves.remove(1));
        }
      }

    /**
     * quits the game
     * 
     * @return
     */
      public static boolean quit() {
        return true;
    }

    /**
     * displays the game board
     */
      public static void displayBoard(PetesPike game) {
        System.out.println(game.toString());
    }

    /*
     * play game
     */
      public static void play(PetesPike game) throws PetesPikeException {
        Scanner in = new Scanner(System.in);
        display(game);
        boolean quit = false;
        while (quit == false) {
            if(game.hasWon()){
              System.out.println("Congratulations, you have finished the game!");
              quit=true;
              continue;
            }
            System.out.print("Command: ");
            String command = in.nextLine();
            String[] splitCommand = command.split(" "); // for "move" and "new" input
            System.out.println();
            switch (splitCommand[0]){
              case HELP:
                    help();
                    break;
                case QUIT:
                    quit = quit();
                    System.out.println("Goodbye!");
                    return;
                case MOVE:
                    move(splitCommand, game);
                    break;
                case RESET:
                    reset();
                    break;
                case HINT:
                    hint(game);
                    break;
                case BOARD:
                    displayBoard(game);
                case NEW:
                    displayBoard(game); 
                    newGame();
            }
          }
      }


    /*
     * main
     */
      public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Puzzle filename: ");
        fileName = in.nextLine();

         try {
            PetesPike game = new PetesPike(fileName);
            help();
            play(game);
        } catch (PetesPikeException e) {
            System.out.println(e.getMessage());
        }

        in.close();
      }

        
}
