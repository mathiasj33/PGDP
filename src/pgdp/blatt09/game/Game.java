package pgdp.blatt09.game;

/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */
public class Game {

    private Position pos;
    private boolean wStarted;
    private GameMove currentGameMove;

    /**
     * Startet ein neues Spiel. Der Benutzer wird ueber das Spielgeschehen
     * informiert.
     *
     * Dazu gehoert auch die Information, wie lange die einzelnen Raubtiere noch
     * ohne Essen auskommen koennen. Diese Information soll auf Anfrage oder
     * immer angezeigt werden.
     *
     * Es soll ausserdem eine Moeglichkeit geben, sich alle Zuege anzeigen zu
     * lassen, die in der Spielsituation moeglich sind.
     *
     * Bei fehlerhaften Eingaben wird die Eingabe natuerlich wiederholt.
     *
     * Der Parameter spezifiziert, wer das Spiel beginnen darf.
     */
    public void startGame(boolean ladiesFirst) {  //TODO: siehe oben
        pos = new Position();
        pos.reset(ladiesFirst ? 'W' : 'M');
        wStarted = ladiesFirst;
        System.out.println(pos);
        startGameLoop();
    }

    private void startGameLoop() {
        boolean print = false;
        while (true) {
            if (print)
                System.out.println(pos);
            print = true;

            currentGameMove = new GameMove();  //TODO: überprüfen, dass number of animals nicht größer als max. anzahl an animals ist!!, auch wenn tier frisst, muss danach der numOfDays runtergesetzt werden! !!!refactoring, leerzeichen bei spielfeldausgabe ändern, angabe nochmal durchgehen
            int nrAnimalsToMove = IO.readIntSafe("How many animals do you want to move? (0 - 4; '5' to show all possible moves; '6' to show predator information), "
                    + "'7' to show the field again)\n", 0, 7);
            switch (nrAnimalsToMove) {
                case 5:
                    printAllPossibleMoves();
                    print = false;
                    continue;
                case 6:
                    printPredatorInfo();
                    print = false;
                    continue;
                case 7:
                    continue;
                case 0:
                    if (wStarted != (pos.getNext() == 'W'))
                        pos.sunset();
                    pos.changeCurrentPlayer();
                    if (pos.gameOver()) {
                        printWinner();
                        return;
                    }
                    continue;
                default:
                    break;
            }

            for (int i = 0; i < nrAnimalsToMove; i++) {
                Animal animal = readValidAnimal();
                if (animal instanceof Predator)
                    currentGameMove.setMovedPredator(true);
                Move move = readValidMove(animal);
                currentGameMove.addMove(move);
            }

            if (wStarted != (pos.getNext() == 'W'))
                        pos.sunset();
            pos.applyMoves(currentGameMove.getMoves());

            if (pos.gameOver()) {
                printWinner();
                return;
            }
        }
    }

    private void printWinner() {
        char winner = pos.theWinner();
        if (winner == 'N') {
            System.out.println("It's a draw.");
            return;
        } else if (winner == 'X') {
            return;
        }
        System.out.println(winner + " won the game.");
    }

    private void printPredatorInfo() {
        String s = "";
        for (Animal a : pos.getPredators()) {
            s += a.toString().charAt(1) + " on field " + a.square + " can still live " + ((Predator) a).getWithoutFood() + " days without food.\n";
        }
        System.out.println(s);
    }

    private void printAllPossibleMoves() {
        String s = "Possible moves:\n";
        for (Move m : pos.getAllPossibleMoves()) {
            s += m + "\n";
        }
        System.out.println(s);
    }

    private Animal readValidAnimal() {
        while (true) {
            String field = readOwnAnimalField();
            Animal animal = pos.getAnimal(field);
            if (currentGameMove.getMovedPredator() && animal instanceof Predator) {
                System.out.println("You cannot move any more predators.");
                continue;
            }
            if (animal.possibleMoves().length == 0) {
                System.out.println("This animal cannot move anywhere.");
                continue;
            }
            if (currentGameMove.hasAlreadyMoved(animal)) {
                System.out.println("You have already moved this animal in your current move.");
                continue;
            }
            return animal;
        }
    }

    private Move readValidMove(Animal animal) {
        while (true) {
            Move[] moves = animal.possibleMoves();

            String targets = getTargetFieldsString(moves);
            System.out.println("This animal can move to positions " + targets + ".");

            String chosenTarget = readField("Where do you want to move it?\n");
            if (!containsTarget(moves, chosenTarget)) {
                System.out.println("The animal cannot move to " + chosenTarget + ".");
                continue;
            }
            if (currentGameMove.alreadyMovedToTarget(chosenTarget)) {
                System.out.println("You have already decided to move an animal to " + chosenTarget + " in your current move.");
                continue;
            }

            return new Move(animal.square, chosenTarget);
        }
    }

    private String readOwnAnimalField() {
        String field = readField("Please type in the field of the animal you wish to move: ");

        if (!pos.fieldOccupied(field) || !pos.animalBelongsToCurrentPlayer(pos.getAnimal(field))) {
            System.out.println("There is no animal that belongs to the player '" + pos.getNext() + "' on the field '" + field + "'.");
            return readOwnAnimalField();
        }

        return field;
    }

    private String readField(String msg) {
        String field;
        do {
            field = IO.readString(msg);
        } while (!pos.isValid(field));
        return field;
    }

    private String getTargetFieldsString(Move[] moves) {
        String s = "";
        for (Move m : moves) {
            s += m.getTo();
            if (!m.equals(moves[moves.length - 1])) {
                s += ",";
            }
        }
        return s;
    }

    private boolean containsTarget(Move[] moves, String target) {
        for (Move m : moves) {
            if (m.getTo().equals(target))
                return true;
        }
        return false;
    }
}
