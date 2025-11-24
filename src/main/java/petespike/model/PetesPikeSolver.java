package petespike.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import petespike.backtracker.Backtracker;
import petespike.backtracker.Configuration;

public class PetesPikeSolver implements Configuration<PetesPikeSolver>{
    private final PetesPike petesPike;
    private final List<Move> moves;


    public PetesPikeSolver(PetesPike petesPike) {
        this.petesPike = petesPike;
        this.moves = new ArrayList<>();
    }
    


    @Override
    public Collection<PetesPikeSolver> getSuccessors() {
        List<PetesPikeSolver> retVal = new ArrayList<>();
        List<Move> possibleMoves = petesPike.getPossibleMoves();
        for (Move m : possibleMoves) {
            try {
                PetesPike copy = new PetesPike(petesPike);
                copy.makeMove(m);
                PetesPikeSolver successor = new PetesPikeSolver(copy);
                successor.moves.addAll(this.moves);
                successor.moves.add(m);
                retVal.add(successor);
            }catch (PetesPikeException e){

            }
        }
        // int length = moves.size();

        return retVal;
    }
    

    /**
     * 
     * @return true if a move performed in valid
     */
    @Override
    public boolean isValid() {


        return true; //not complete
    }


    /**
     * Checks if the last move made was valid and if pete 
     * has reached goal
     * @return true if pete has reached  goal
     */
    @Override
    public boolean isGoal() {
        return isValid() == true && petesPike.hasWon();
    }
    

    /**
     * 
     * @return the list of moves performed on the board
     */
    public List<Move> getMoves() {
        return moves;
    }  
    
    public PetesPikeSolver solve(PetesPike petesPike) {
        PetesPike copy = new PetesPike(petesPike);   // work on safe copy
        PetesPikeSolver start = new PetesPikeSolver(copy);

        Backtracker<PetesPikeSolver> bt = new Backtracker<>(false);
        return bt.solve(start);
    }


    public PetesPikeSolver solve(PetesPike petesPike, boolean debug) {
        PetesPike copy = new PetesPike(petesPike);
        PetesPikeSolver start = new PetesPikeSolver(copy);

        Backtracker<PetesPikeSolver> bt = new Backtracker<>(debug);
        return bt.solve(start);
    }
    

    @Override
    public String toString() {
        String result = "";
        result += "Moves so far: " + moves.size();

        for (Move m : moves) {
            result += m.toString();
        }

        result += "Board:";
        result += petesPike.toString();

        return result;
    }
    
    
    
}
