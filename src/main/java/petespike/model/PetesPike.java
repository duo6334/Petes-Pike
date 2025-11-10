package petespike.model;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import petespike.view.AsciiColorCodes;

public class PetesPike {
    private char MOUNTAIN_SYMBOL = 'T';
    public char EMPTY_SYMBOL = '-';
    public char PETE_SYMBOL = 'P';
    private int moveCount;
    private int rows;
    private int cols;
    private GameState gameState;
    private Position mountainTop;
    private String[][] board;
    private Position pete;
    private List<Position> peices = new ArrayList<>();
    private String[] nextColor= new String[9];


    public PetesPike(String filename) throws PetesPikeException{
        //sets the game to be a new game
        this.gameState=GameState.NEW;
        //creates the array of colors to pull from for characters that are not pete
        this.nextColor[0]=AsciiColorCodes.BLUE;
        this.nextColor[1]=AsciiColorCodes.GREEN;
        this.nextColor[2]=AsciiColorCodes.RED;
        this.nextColor[3]=AsciiColorCodes.ORANGE;
        this.nextColor[4]=AsciiColorCodes.CYAN;
        this.nextColor[5]=AsciiColorCodes.PURPLE;
        this.nextColor[6]=AsciiColorCodes.YELLOW;
        this.nextColor[7]=AsciiColorCodes.MAGENTA;
        this.nextColor[8]=AsciiColorCodes.LT_GRAY;
        //creates the variable to use to iterate through the colors for peices
        int color=0;
        try{
            //reads the file
            FileReader reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);

            //takes the first line, which has rows and columns
            String rowsAndColumns = scanner.nextLine();
            String[] rowsAndColumnsParts = rowsAndColumns.split(" ");

            //makes the board using the number of rows and columns
            this.rows = Integer.valueOf(rowsAndColumnsParts[0]);
            this.cols=Integer.valueOf(rowsAndColumnsParts[1]);
            this.board =new String[rows][cols];
            

            //this fills in the board, iterates over the rows to create a 2d array
            for(int i=0;i<this.rows;i++){
                String boardRow=scanner.nextLine();
                //this uses the scanner to look through the lines, and fill in the board according to the file
                for(int j = 0; j < boardRow.length(); j++){
                    if(boardRow.charAt(j)=='T'){
                        //takes special note of the mountain top, and stores it for easy reference
                        this.mountainTop=new Position(i,j);
                        this.board[i][j]="T";
                    }else if(boardRow.charAt(j)=='P'){
                        //takes special note of pete, and stores it for easy reference
                        this.pete=new Position(i,j);
                        this.board[i][j]=AsciiColorCodes.GOLD+"P"+AsciiColorCodes.RESET;
                        this.peices.add(this.pete);
                    }else if(boardRow.charAt(j)!='-'){
                        //takes special note of all peices, and stores them for easy reference particularly in finding available moves
                        this.peices.add(new Position(i,j));
                        this.board[i][j]=nextColor[color]+"G"+AsciiColorCodes.RESET;
                        color+=1;
                    }else{
                        this.board[i][j]="-";
                    }
                }
            }
            scanner.close();
        }catch(IOException e){
            throw new PetesPikeException("File not found");
        }
    }

    public int getMoveCount(){
        return this.moveCount;
    }

    public int getRows(){
        return this.rows;
    }

    public int getCols(){
        return this.cols;
    }

    public GameState getGameState(){
        return this.gameState;
    }



    public void makeMove(Move move) throws PetesPikeException{
        String piece = getSymbolAt(move.getPosition());
        String blank = "-";
        if (move.getDirection() == Direction.UP){
            int i = (move.getPosition().getRow() - 1);
            while(board[i][move.getPosition().getCol()].equals(blank)){
                if (i == 0) {
                    throw new PetesPikeException("Illegal move: piece will fall off mountain!");
                }
                i--;
            }
            board[i][move.getPosition().getCol()] = piece;
            board[move.getPosition().getRow()][move.getPosition().getCol()] = blank;
        }
        else if (move.getDirection() == Direction.DOWN){
            int i = (move.getPosition().getRow() + 1);
            while(board[i][move.getPosition().getCol()].equals(blank)){
                if (i > getRows()) {
                    throw new PetesPikeException("Illegal move: piece will fall off mountain!");
                }
                i++;
            }
            board[i][move.getPosition().getCol()] = piece;
            board[move.getPosition().getRow()][move.getPosition().getCol()] = blank;
        }
        else if (move.getDirection() == Direction.LEFT){
            int i = (move.getPosition().getCol() - 1);
            while(board[move.getPosition().getRow()][i].equals(blank)){
                if (i == 0) {
                    throw new PetesPikeException("Illegal move: piece will fall off mountain!");
                }
                i--;
            }
            board[move.getPosition().getRow()][i] = piece;
            board[move.getPosition().getRow()][move.getPosition().getCol()] = blank;
        }
        else if (move.getDirection() == Direction.RIGHT){
            int i = (move.getPosition().getCol() + 1);
            while(board[move.getPosition().getRow()][i].equals(blank)){
                if (i > getCols()) {
                    throw new PetesPikeException("Illegal move: piece will fall off mountain!");
                }
                i++;
            }
            board[move.getPosition().getRow()][i] = piece;
            board[move.getPosition().getRow()][move.getPosition().getCol()] = blank;
        }
        moveCount++;
    }

    public String getSymbolAt(Position position){
        return board[position.getRow()][position.getCol()]; //had to change this for my code to work but did not double check if this is correct
    }

    public Position getMountaintop(Position position){
        return this.mountainTop;
    }

    public List<Move> getPossibleMoves(){
        //creates the list to store moves
        List<Move> result = new ArrayList<>();
        //iterates through all peices we have
        for(Position peice:peices){
            //stores the row and column for each peice
            int row = peice.getRow();
            int col = peice.getCol();
            
            //looks at each direction
                //up
                for (int j = row-1; j >= 0; j--) {
                    //checks if there is a peice to land against, and if so add it to the available moves
                    if(!board[j][col].equals("-")&&!board[j][col].equals("T")){ 
                        result.add(new Move(peice,Direction.UP));
                        break;
                    }
                }
                //Left
                for (int j = col-1; j >= 0; j--) {
                    //checks if there is a peice to land against, and if so add it to the available moves
                    if(!board[row][j].equals("-")&&!board[row][j].equals("T")){
                        result.add(new Move(peice,Direction.LEFT));
                        break;
                    }
                }
                //Down
                for (int j = row+1; j < this.rows; j++) {
                    //checks if there is a peice to land against, and if so add it to the available moves
                    if(!board[row][j].equals("-")&&!board[row][j].equals("T")){
                        result.add(new Move(peice,Direction.DOWN));
                        break;
                    }
                }
                //Right
                for (int j = col+1; j < this.cols; j++) {
                    //checks if there is a peice to land against, and if so add it to the available moves
                    if(!board[row][j].equals("-")&&!board[row][j].equals("T")){
                        result.add(new Move(peice,Direction.RIGHT));
                        break;
                    }
                }
            }
        
        return result;
    }

    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result += board[i][j];
            }
            result+="\n";
        }
        return result;
    }
}

