package petespike.model;

import java.io.FileReader;
import java.io.IOException;
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
    private Position pete;
    private List<Position> peices;

    public PetesPike(String filename){
        this.gameState=GameState.NEW;
        try{
            FileReader reader = new FileReader(filename);
            Scanner scanner = new Scanner(reader);
            String rowsAndColumns = scanner.nextLine();
            String[] rowsAndColumnsParts = rowsAndColumns.split(" ");
            this.rows = Integer.valueOf(rowsAndColumnsParts[0]);
            this.cols=Integer.valueOf(rowsAndColumnsParts[1]);
            this.board =new char[this.rows][this.cols];
            for(int i=0;i<this.rows;i++){
                String boardRow=scanner.nextLine();
                for(int j = 0; j < boardRow.length(); i++){
                    if(boardRow.charAt(j)=='T'){
                        this.mountainTop=new Position(j,i);
                        this.board[i][j]='T';
                    }else if(boardRow.charAt(j)=='P'){
                        this.pete=new Position(j,i);
                        this.board[i][j]='P';
                    }else if(boardRow.charAt(j)=='P'){
                        this.pete=new Position(j,i);
                        this.board[i][j]='P';
                }
            }
        }catch(IOException e){
            throw new PetesPikeException();
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

