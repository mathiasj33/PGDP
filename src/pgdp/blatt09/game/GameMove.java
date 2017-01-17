package pgdp.blatt09.game;

public class GameMove {

    private List<Move> fixedMoves = new List<>();
    private boolean movedPredator;

    public void addMove(Move m) {
        fixedMoves.add(m);
    }

    public boolean alreadyMovedToTarget(String target) {
        for (int i = 0; i < fixedMoves.size(); i++) {
            Move m = fixedMoves.get(i);
            if (m.getTo().equals(target))
                return true;
        }
        return false;
    }

    public boolean hasAlreadyMoved(Animal a) {
        for (int i = 0; i < fixedMoves.size(); i++) {
            if (fixedMoves.get(i).getFrom().equals(a.square))
                return true;
        }
        return false;
    }
    
    public Move[] getMoves() {
        return fixedMoves.toArray(new Move[fixedMoves.size()]);
    }

    public boolean getMovedPredator() {
        return movedPredator;
    }

    public void setMovedPredator(boolean movedPredator) {
        this.movedPredator = movedPredator;
    }
}
