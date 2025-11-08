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

    public PetesPike(String filename){
        this.gameState=GameState.NEW;
        try{
            FileReader reader = new FileReader(filename);

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
        if (move.getDirection() == Direction.UP){

        }
        else if (move.getDirection() == Direction.DOWN){

        }
        else if (move.getDirection() == Direction.LEFT){
            
        }
        else if (move.getDirection() == Direction.RIGHT){
            
        }
        moveCount++;
    }

    public char getSymbolAt(Position position){
        
    }

    public Position getMountaintop(Position position){
        return this.mountainTop;
    }

    public List<Move> getPossibleMoves(){
        
    }

}

