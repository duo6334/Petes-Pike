package petespike.view;

import petespike.model.PetesPike;

public class PetesPikeCLI {
    public static final String HELP = "help";
    public static final String BOARD = "board";
    public static final String RESET = "reset";
    public static final String NEW = "new";
    public static final String MOVE = "move";
    public static final String HINT = "hint";
    public static final String QUIT = "quit";

    /**
     * Display commands
     */

     public static void help(){
        System.out.println("Commands:");
        System.out.println("help - show commands");
        System.out.println("board - display current board");
        System.out.println("reset - reset puzzle to initial state");
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

      }

    /*
     * start new puzzle
     */

      public static void newGame(){

      }

    /*
     * valid move
     */

      public static void hint(){

      }

    /*
     * main
     */
      public static void main(String[] args) {
            
      }

        
}
