package petespike.model;

public interface PetesPikeObserver {

    /**
     * Method is called by PetesPike class when a piece 
     * has been successfully moved
     * 
     * @param from  The position a piece is moving from
     * @param to The position a piece is moving to
     */
    void pieceMoved(Position from, Position to);
}
