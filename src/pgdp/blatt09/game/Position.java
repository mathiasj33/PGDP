package pgdp.blatt09.game;

/**
 * Die Klasse Position repraesentiert eine Spielsituation.
 *
 */
public class Position {
    
    /**
     * Die Tiere werden intern in einem Array gespeichert.
     * nrAnimals gibt an, wie viele Tiere auf dem Brett sind.
     * Diese sind in myAnimals an den Positionen 0 bis nrAnimals-1 enthalten.
     *
     * Es ist empfohlen, aber nicht vorgeschrieben, diese Attribute zu verwenden.
     *
     * Falls die beiden Attribute NICHT verwendet werden, muss die Ausgabe
     * der Spielposition unten entsprechend auf die verwendete Datenstruktur
     * angepasst werden. Die toString-Methode darf dabei nicht veraendert werden,
     * muss jedoch die selbe Rueckgabe liefern. D.h. es ist dann notwendig,
     * die Hilfsmethode boardRepresentation auf die verwendete Datenstruktur anzupassen.
     */
    private Animal[] myAnimals;
    private int nrAnimals;

    /**
     * Spieler, der als naechstes ziehen darf ('M' oder 'W').
     * Wird jedes Mal aktualisiert, wenn eine Seite ihre Zuege ausfuehrt.
     */
    private char next = 'W';

    public Position() {
        myAnimals = new Animal[32];
    }
    
    /**
     * Stellt die Anfangsposition des Spiels her.
     * Der Parameter gibt an, welche Seite beginnt ('M' oder 'W').
     */
    public void reset(char movesNext) {
        next = movesNext;
        myAnimals = new Animal[2];
        nrAnimals = 0;
//        initSide(true);
//        initSide(false);
        
//        addAnimal(new Leopard(false, "a2", this));
        addAnimal(new Rabbit(true, "a4", this));
//        addAnimal(new Rabbit(true, "c4", this));
        addAnimal(new Rabbit(false, "d4", this));
//        addAnimal(new Leopard(false, "a6", this));
    }
    
    private void initSide(boolean w) {
        int front = w ? 2 : 7;
        int back = w ? 1 : 8;
        
        addAnimal(new Penguin(w, "a" + front, this));
        addAnimal(new Penguin(w, "h" + front, this));
        
        addAnimal(new Rabbit(w, "b" + front, this));
        addAnimal(new Rabbit(w, "c" + front, this));
        addAnimal(new Rabbit(w, "d" + front, this));
        addAnimal(new Rabbit(w, "e" + front, this));
        addAnimal(new Rabbit(w, "f" + front, this));
        addAnimal(new Rabbit(w, "g" + front, this));
        
        addAnimal(new Snake(w, "a" + back, this));
        addAnimal(new Snake(w, "h" + back, this));
        
        addAnimal(new Elephant(w, "b" + back, this));
        addAnimal(new Elephant(w, "g" + back, this));
        
        addAnimal(new Horse(w, "c" + back, this));
        addAnimal(new Horse(w, "f" + back, this));
        
        addAnimal(new Leopard(w, "d" + back, this));
        addAnimal(new Leopard(w, "e" + back, this));
    }
    
    public boolean isValid(String square) {
        Vector v;
        try {
            v = VectorUtils.squareToVector(square);
        } catch(IllegalArgumentException e) {
            return false;
        }
        if(v.x < 0 || v.x > 7 || v.y < 0 || v.y > 7) return false;
        return true;
    }
    
    public boolean fieldOccupied(String square) {
        for(Animal a : myAnimals) {
            if(a == null) continue;
            if(a.square.equals(square)) return true;
        }
        return false;
    }
    
    public Animal getAnimal(String square) {
        if(!isValid(square)) throw new IllegalArgumentException("This square is not valid: " + square);
        for(Animal a : myAnimals) {
            if(a.square.equals(square)) return a;
        }
        throw new IllegalStateException("There is no animal on " + square);
    }

    public boolean animalBelongsToCurrentPlayer(Animal animal) {
        return animal.female && next == 'W' || !animal.female && next == 'M';
    }
    
    public Move[] getAllPossibleMoves() {
        List<Move> moves = new List<>();
        for(Animal a : myAnimals) {
            if(animalBelongsToCurrentPlayer(a)) moves.addAll(a.possibleMoves());
        }
        return moves.toArray(new Move[moves.size()]);
    }
    
    private void addAnimal(Animal a) {
        myAnimals[nrAnimals] = a;
        nrAnimals++;
    }

    /**
     * Fuehrt die uebergebenen Zuege fuer einen der Spieler aus.
     * Die Reihenfolge soll keinen Unterschied machen.
     * Diese Methode geht davon aus, dass dies bereits ueberprueft wurde.
     *
     * Der Zustand des Spiels wird entsprechend angepasst, d. h. ein Spiel
     * kann von der Anfangsposition aus allein mittels Aufrufen dieser Methode
     * gespielt werden. Insbesondere wechselt durch den Aufruf das Zugrecht,
     * da M und W abwechselnd ziehen.
     *
     * @param move Array mit den Zuegen, die ausgefuehrt werden sollen.
     *
     */
    public void applyMoves(Move[] moves){
        for(Move m : moves) {
            Animal animal = getAnimal(m.getFrom());
            String newSquare = m.getTo();
            if(fieldOccupied(newSquare)) {
                deleteAnimal(getAnimal(newSquare));
                ((Predator) animal).resetWithoutFood();
            }
            animal.square = newSquare;
        }
        changeCurrentPlayer();
    }

    public void deleteAnimal(Animal a) {
        myAnimals = ArrayUtils.copyAndDelete(myAnimals, new Animal[myAnimals.length - 1], a);
        nrAnimals--;
    }
    
    public void sunset() {
        for(Animal a : myAnimals) a.sunset();
    }
    
    public void changeCurrentPlayer() {
        next = next == 'W' ? 'M' : 'W';
    }

    /**
     * Ermittelt, ob/wer gewonnen hat.
     *
     * @return 'W' falls W gewonnen hat,
     *         'M' falls M gewonnen hat,
     *         'N' falls das Spiel unentschieden zu Ende ist,
     *         'X' falls das Spiel noch nicht zu Ende ist.
     *
     */
    public char theWinner() {
        if(nrAnimals == 0) return 'N';
        else if(getNumAnimalsOfPlayer(true) == 0) return 'M';
        else if(getNumAnimalsOfPlayer(false) == 0) return 'W';
        else if(getPredators().length == 0) {
            if(getNumAnimalsOfPlayer(true) > getNumAnimalsOfPlayer(false)) return 'W';
            else if(getNumAnimalsOfPlayer(true) < getNumAnimalsOfPlayer(false)) return 'M';
            else {
                return 'N';
            }
        }
        return 'X';
    }
    
    public boolean gameOver() {
        return theWinner() != 'X';
    }
    
    private int getNumAnimalsOfPlayer(boolean w) {
        int count = 0;
        for(Animal a : myAnimals) {
            if(a.female == w) count++;
        }
        return count;
    }

    public char getNext() {
        return next;
    }

    public void setNext(char next) {
        this.next = next;
    }

    public Animal[] getPredators() {
        List<Animal> predators = new List<>();
        for(Animal a : myAnimals) {
            if(a instanceof Predator) predators.add(a);
        }
        return predators.toArray(new Predator[predators.size()]);
    }
    // Ausgabe der Spielposition

    private static final int[] I = {8,7,6,5,4,3,2,1};
    private static final String[] J = {"a","b","c","d","e","f","g","h"};
    private static int toIndex(String s){return (s.charAt(0)-'a');}

    // Erzeugt eine 2D-Repraesentation der Spielposition.
    // Darf ggf. auf neue Datenstruktur angepasst werden (s.o.)
    // Die Rueckgabe ist ein zweidimensionales Array, welches
    // jedem Feld das darauf befindliche Tier (oder null) zuordnet.
    // Dabei laeuft der erste Index von der 'a'-Linie zur 'h'-Linie,
    // der zweite von der 1. zur 8. Reihe. D.h. wenn z.B. bei a[3][7]
    // ein Tier ist, ist das zugehörige Feld "d8" (vierter Buchstabe,
    // achte Zahl).
    public Animal[][] boardRepresentation(){
        Animal[][] a = new Animal[8][8];
        for (int i : I) {
            for (String j : J) {
                for (int k = 0; k < myAnimals.length; k++) {
                    if (null == myAnimals[k]) {break;}
                    if (myAnimals[k].square.equals(j+i)) {
                        a[toIndex(j)][i-1] = myAnimals[k];
                    }
                }
            }
        }
        return a;
    }


    @Override
    public String toString(){
        String str = "   a b c d e f g h\n";
        Animal[][] ani = boardRepresentation();
        for (int i : I) {
            str += (i+" ");
            for (String j : J) {
                if (null == ani[toIndex(j)][i-1]) {
                    str += (i+toIndex(j))%2==1 ? Globals.ts_empty_square_dark : Globals.ts_empty_square_light;
                } else {
                    str += ani[toIndex(j)][i-1].toString();
                }
            }
            str += " " + i + "\n";
        }
        str += "   a b c d e f g h\nIt is " + next + "'s turn.\n";
        return str;
    }
}
