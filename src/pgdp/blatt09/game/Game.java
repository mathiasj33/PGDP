package pgdp.blatt09.game;

/**
 * Die Klasse Game fuehrt die Benutzerinteraktion durch.
 *
 */
public class Game {

    private Position pos;
    private boolean wStarted;
    private GameMove currentGameMove;
    private Console console;
    private InputHandler inputHandler;

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
        console = new Console(pos);
        inputHandler = new InputHandler(this);
        startGameLoop();
    }

    private void startGameLoop() {
        while (true) {
            console.printGame();
            console.setPrint(true);
            currentGameMove = new GameMove();  //TODO: testen, leerzeichen bei spielfeldausgabe ändern, angabe nochmal durchgehen

            int nrAnimalsToMove = IO.readIntSafe("How many animals do you want to move? (0 - 4; '5' to show all possible moves; '6' to show predator information), "
                    + "'7' to show the field again)\n", 0, 7);

            if (nrAnimalsToMove == 0 || nrAnimalsToMove > 4) {
                inputHandler.executeSpecialAction(nrAnimalsToMove);
                continue;
            }

            if (nrAnimalsToMove > pos.getNumAnimalsOfCurrentPlayer()) {
                IO.readString("You cannot move " + nrAnimalsToMove + " animals as you only have " + pos.getNumAnimalsOfCurrentPlayer() + " left.");
                System.out.println("\n");
                continue;
            }

            for (int i = 0; i < nrAnimalsToMove; i++) {
                Animal animal = readValidAnimal();
                if (animal instanceof Predator)
                    currentGameMove.setMovedPredator(true);
                Move move = readValidMove(animal);
                currentGameMove.addMove(move);
            }

            boolean wasEndOfRound = endOfRound();
            pos.applyMoves(currentGameMove.getMoves());

            if (wasEndOfRound)
                pos.sunset();

            if (pos.gameOver()) {
                console.printWinner();
                return;
            }
        }
    }

    public boolean endOfRound() {
        return wStarted != (pos.getNext() == 'W');
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

    private String readOwnAnimalField() {
        String field = readField("Please type in the field of the animal you wish to move: ");

        if (!pos.fieldOccupied(field) || !pos.animalBelongsToCurrentPlayer(pos.getAnimal(field))) {
            System.out.println("There is no animal that belongs to the player '" + pos.getNext() + "' on the field '" + field + "'.");
            return readOwnAnimalField();
        }

        return field;
    }

    private Move readValidMove(Animal animal) {
        while (true) {
            console.printMoves(animal);
            
            String chosenTarget = readField("Where do you want to move it?\n");
            if (!animal.canMoveTo(chosenTarget)) {
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

    private String readField(String msg) {
        String field;
        do {
            field = IO.readString(msg);
        } while (!pos.isValid(field));
        return field;
    }

    public Position getPosition() {
        return pos;
    }

    public Console getConsole() {
        return console;
    }
}
