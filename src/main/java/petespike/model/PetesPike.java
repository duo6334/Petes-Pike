package petespike.model;

import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class PetesPike {
    private char MOUNTAIN_SYMBOL = 'T';
    public char EMPTY_SYMBOL = '-';
    public char PETE_SYMBOL = 'P';
    private int moveCount;
    private int rows;
    private int cols;
    private GameState gameState;
    private Position mountainTop;
    private char[][] board;

    public PetesPike(String filename){
        this.gameState=GameState.NEW;
        try{
            FileReader reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);
            String rowsAndColumns = scanner.nextLine();
            String[] rowsAndColumnsParts = rowsAndColumns.split(" ");
            this.rows = rowsAndColumnsParts[0];
            this.cols=rowsAndColumnsParts[1];
        }catch(IOException e){

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

    public void makeMove(Move move){

    }

    public char getSymbolAt(Position position){

    }

    public Position getMountaintop(Position position){
        return this.mountainTop;
    }

    public List<Move> getPossibleMoves(){
        
    }

}

