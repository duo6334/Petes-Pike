package petespike.backtracker;

import java.util.Collection;
import java.util.List;

import petespike.model.*;

public class PetesPikeSolver implements Configuration{
    PetesPike petesPike;
    List<Move> moves;


    public PetesPikeSolver(PetesPike petesPike){
        this.petesPike=petesPike;
    }


    @Override
    public Collection getSuccessors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSuccessors'");
    }


    @Override
    public boolean isValid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }


    @Override
    public boolean isGoal() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGoal'");
    }

    public List<Move> getMoves() {
        return moves;
    }

    public PetesPikeSolver solve(PetesPike petesPike){

    }

    public PetesPikeSolver solve(PetesPike petesPike, Boolean debug){

    }
}
