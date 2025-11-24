package petespike.backtracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import petespike.model.*;

public class PetesPikeSolver implements Configuration<PetesPikeSolver>{
    PetesPike petesPike;
    List<Move> moves= new ArrayList<>();


    public PetesPikeSolver(PetesPike petesPike){
        this.petesPike=petesPike;
    }


    @Override
    public Collection<PetesPikeSolver> getSuccessors(){
        try {
            
        
        List<PetesPikeSolver> successors = new ArrayList<>();

        for (Move move : petesPike.getPossibleMoves()) {

            PetesPike copy = new PetesPike(petesPike);

            // Apply the move to the clone
            copy.makeMove(move);

            // Create a NEW solver object based on the cloned puzzle
            PetesPikeSolver next = new PetesPikeSolver(copy);

            // Copy the move history
            next.moves.addAll(this.moves);
            next.moves.add(move);

            successors.add(next);
        }

        return successors;

        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }


    @Override
    public boolean isValid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }


    @Override
    public boolean isGoal() {
        return petesPike.getPete().equals(petesPike.getMountaintop());
    }

    public List<Move> getMoves() {
        return moves;
    }

    public PetesPikeSolver solve(PetesPike petesPike){
        return solve(petesPike, false);
    }

    public PetesPikeSolver solve(PetesPike petesPike, Boolean debug){
        Backtracker<PetesPikeSolver> bt = new Backtracker<>(debug);
        return bt.solve(new PetesPikeSolver(petesPike));

    }
}
