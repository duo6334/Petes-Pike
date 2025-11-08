package petespike.view;

import petespike.model.Move;
import petespike.model.PetesPike;

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
      }

    /**
     * Attempt to make a move.
     */
      public static void move(String[] parts, PetesPike game) {
        
      }

    /*
     * reset game
     */

      public static void reset(){
        try {
            PetesPike newGame = new PetesPike(fileName);
        } catch (PetesPikeException e) {
            System.out.println(e.getMessage());
        }
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
         
      }

    /*
     * play game
     */
      public static void play(PetesPike game) {
      }


    /*
     * main
     */
      public static void main(String[] args) {
            
      }

        
}
