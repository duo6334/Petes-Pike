package petespike.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import backtracker.Configuration;

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
        int length = moves.size();
        
        return null;   // someone can pick this up
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
        if (moves.size() < 1) {
            return null;
        }
        return moves;
    }  
    
    public PetesPikeSolver solve(PetesPike petesPike) {
        return null;
    }


    public PetesPikeSolver solve(PetesPike petesPike, boolean debug) {
        return null;
    }
    


    
    
    
}
