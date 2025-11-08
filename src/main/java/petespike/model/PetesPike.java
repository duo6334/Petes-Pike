package petespike.model;

import java.util.List;

public class PetesPike {
    private char MOUNTAIN_SYMBOL = 'T';
    public char EMPTY_SYMBOL = '-';
    public char PETE_SYMBOL = 'P';
    private int moveCount;
    private int rows;
    private int cols;
    private GameState gameState;

    public PetesPike(String filename){
        this.gameState=GameState.NEW;

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

    }

    public List<Move> getPossibleMoves(){

    }

}

