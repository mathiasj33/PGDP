package pgdp.blatt09.game;

public class InputHandler {

    private Game game;
    private Console console;
    private Position pos;

    public InputHandler(Game game) {
        this.game = game;
        this.console = game.getConsole();
        this.pos = game.getPosition();
    }

    public void executeSpecialAction(int input) {
        switch (input) {
            case 5:
                console.printAllPossibleMoves();
                console.setPrint(false);
                break;
            case 6:
                console.printPredatorInfo();
                console.setPrint(false);
                break;
            case 7:
                break;
            case 0:
                if (game.endOfRound())
                    pos.sunset();
                pos.changeCurrentPlayer();
                break;
            default:
                break;
        }
    }
}
