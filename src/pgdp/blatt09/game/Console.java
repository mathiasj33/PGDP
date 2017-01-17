package pgdp.blatt09.game;

public class Console {

    private final Position pos;
    private boolean print = true;
    
    public Console(Position pos) {
        this.pos = pos;
    }
    
    public void printGame() {
        if(print)
        System.out.println(pos);
    }
    
    public void printWinner() {
        char winner = pos.theWinner();
        if (winner == 'N') {
            System.out.println("It's a draw.");
            return;
        } else if (winner == 'X') {
            return;
        }
        System.out.println(winner + " won the game.");
    }

    public void printPredatorInfo() {
        String s = "";
        for (Animal a : pos.getPredators()) {
            char symbol = a.toString().charAt(1);
            s += symbol + " on field " + a.square + " can still live " + ((Predator) a).getWithoutFood() + " days without food.\n";
        }
        System.out.println(s);
    }

    public void printAllPossibleMoves() {
        String s = "Possible moves:\n";
        for (Move m : pos.getAllPossibleMoves()) {
            s += m + "\n";
        }
        System.out.println(s);
    }

    public void printMoves(Animal a) {
        String s = "";
        Move[] moves = a.possibleMoves();
        for (Move m : moves) {
            s += m.getTo();
            if (!m.equals(moves[moves.length - 1])) {
                s += ",";
            }
        }
        System.out.println("This animal can move to positions " + s + ".");
    }
    
    public void setPrint(boolean print) {
        this.print = print;
    }
}
