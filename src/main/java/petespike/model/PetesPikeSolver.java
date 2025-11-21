package petespike.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PetesPikeSolver {
    private final PetesPike petesPike;
    private final List<Move> moves;


    public PetesPikeSolver(PetesPike petesPike) {
        this.petesPike = petesPike;
        this.moves = new ArrayList<>();
    }
    


    public Collection<PetesPikeSolver> getSuccessors() {
        return null;
    }
    

    public boolean isValid() {
        return true;
    }


    /**
     * Checks if the last move made was valid and if pete 
     * has reached goal
     * @return true if pete has reached  goal
     */
    public boolean isGoal() {
        return isValid() == true && petesPike.hasWon();
    }
    
    public List<Move> getMoves() {
        return null;
    }  
    
    public PetesPikeSolver solve(PetesPike petesPike) {
        return null;
    }


    public PetesPikeSolver solve(PetesPike petesPike, boolean debug) {
        return null;
    }
    


    
    
    
}
