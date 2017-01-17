package pgdp.blatt05;

import pgdp.global.MiniJava;

public class Linja extends MiniJava {

    private static int[][] spielfeld = new int[8][6];
    private static SimpleIntList stonesInTarget;
    private static boolean bonusMove;
    private static int[] points = new int[2];

    /**
     * initialisiert das Spielfeld Ziellinie fuer Spieler 1 ist Zeile 7
     * Ziellinie fuer Spieler -1 ist Zeile 0
     */
    private static void initSpiel() {
        for (int i = 0; i < spielfeld.length; i++) {
            if (i != 0 && i != spielfeld.length - 1) {
                spielfeld[i] = new int[]{-(12 - i + 1), 0, 0, 0, 0, 6 + i};
            }
            if (i == 0) {
                spielfeld[i] = new int[]{1, 2, 3, 4, 5, 6};
            }
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new int[]{-6, -5, -4, -3, -2, -1};
            }
        }
        stonesInTarget = new SimpleIntList();
    }

    /**
     *
     * @return formatiertes aktuelles Spielfeld
     */
    private static String output() {
        String tmp = "Spieler 1 spielt von oben nach unten\n" + "Spieler -1 spielt von unten nach oben\n";
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                tmp = tmp + "\t" + spielfeld[i][j];
            }
            tmp = tmp + "\n";
        }
        return tmp;
    }

    /**
     * @return true, wenn die Eingabe stein im richtigen Wertebereich liegt und
     * zum Spieler gehoert; false, sonst
     */
    private static boolean gueltigeEingabe(int stein, int spieler) {
        if (spieler == 1) {
            return stein >= 1 && stein <= 12;
        } else if (spieler == -1) {
            return stein >= -12 && stein <= -1;
        }
        throw new IllegalArgumentException("Ung�ltiger Spieler.");
    }

    /**
     * @param stein kann Werte -1 bis -12 und 1 bis 12 haben
     * @return gibt x-Koordinate von stein an Position 0 und die y-Koordinaten
     * von stein an Position 1 zurueck; falls stein nicht gefunden, wird {-1,-1}
     * zurueckgegeben
     */
    private static int[] findeStein(int stein) {
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                if (spielfeld[i][j] == stein) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * @param reihe hat Werte 0 bis 7
     * @return Anzahl der Steine in einer Reihe
     */
    private static int steineInReihe(int reihe) {
        return steineInReihe(1, reihe) + steineInReihe(-1, reihe);
    }

    private static int steineInReihe(int player, int reihe) {
        int count = 0;
        for (int i = 0; i < spielfeld[reihe].length; i++) {
            if (spielfeld[reihe][i] > 0 && player == 1 || spielfeld[reihe][i] < 0 && player == -1)
                count++;
        }
        return count;
    }

    /**
     * Ueberprueft, ob der Zug zulaessig ist und fuehrt diesen aus, wenn er
     * zulaessig ist.
     *
     * @param vorwaerts == true: Zug erfolgt vorwaerts aus Sicht des
     * Spielers/Steins vorwearts == false: Zug erfolgt rueckwaerts aus Sicht des
     * Spielers/Steins
     * @return Rueckgabe -1: Zug nicht zulaessig Rueckgabe 0-5: Weite des
     * potentiellen naechsten Zugs (falls Folgezug folgt) Rueckgabe 6: Ziellinie
     * wurde genau getroffen (potentieller Bonuszug)
     *
     */
    private static int setzeZug(int stein, int weite, boolean vorwaerts) {
        int[] coords = findeStein(stein);
        int player = stein >= 1 ? 1 : -1;

        boolean figureAlreadyInTarget = false; // damit auch Steine, die das
        // Ziel schon erreicht haben
        // durch den Bonuszug wieder
        // zur�ckgestellt werden k�nnen

        if (coords[0] == -1 && coords[1] == -1) {
            if (bonusMove && gueltigeEingabe(stein, player)) { // es gibt einen
                // Bonuszug und
                // der Stein
                // wird nicht
                // gefunden ->
                // er ist schon
                // im Ziel
                coords[0] = player == 1 ? 7 : 0;
                figureAlreadyInTarget = true;
            } else {
                write("Der Stein " + stein + " hat entweder die Ziellinie bereits erreicht oder er existiert nicht.");
                return -1;
            }
        }

        if (player == -1)
            weite = -weite;
        if (!vorwaerts)
            weite = -weite;
        int newRow = coords[0] + weite;

        if (!isInRange(player, newRow)) {
            write("Der Zug ist ung�ltig, da der Stein " + stein + " nicht noch weiter zur�ck kann.");
            return -1;
        }

        if (reachedTarget(player, newRow)) {
            if (reachedTargetExactly(player, newRow))
                bonusMove = true;
            spielfeld[coords[0]][coords[1]] = 0;
            stonesInTarget.add(stein);
            return 0;
        }

        if (steineInReihe(newRow) >= 6) {
            write("Der Zug ist ung�ltig, da in Reihe " + newRow + " sonst mehr als 6 Steine w�ren.");
            return -1;
        }

        if (!figureAlreadyInTarget)
            spielfeld[coords[0]][coords[1]] = 0;
        spielfeld[newRow][getFreePos(newRow)] = stein;

        int nextMoveAmount = steineInReihe(newRow) - 1;
        return nextMoveAmount;
    }

    private static int getFreePos(int row) {
        for (int i = 0; i < spielfeld[row].length; i++) {
            if (spielfeld[row][i] == 0)
                return i;
        }
        return -1;
    }

    private static boolean isInRange(int player, int row) {
        return (player == 1 && row >= 0) || (player == -1 && row <= 7);
    }

    private static boolean reachedTarget(int player, int row) {
        return (player == 1 && row >= 7) || (player == -1 && row <= 0);
    }

    private static boolean reachedTargetExactly(int player, int row) {
        return (player == 1 && row == 7) || (player == -1 && row == 0);
    }

    /**
     * @return true, falls die Bedingungen des Spielendes erfuellt sind, d.h.
     * alle Steine des einen Spielers sind an den Steinen des gegnerischen
     * Spielers vorbeigezogen
     *
     */
    private static boolean spielende() {
        int lastRowP1 = 7;
        int lastRowPMinus1 = 0;
        boolean containsP1Stone = false;
        boolean containsPMinusOneStone = false;
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                if (spielfeld[i][j] > 0 && i < lastRowP1) {
                    lastRowP1 = i;
                    containsP1Stone = true;
                } else if (spielfeld[i][j] < 0 && i > lastRowPMinus1) {
                    lastRowPMinus1 = i;
                    containsPMinusOneStone = true;
                }
            }
        }
        if (!containsP1Stone || !containsPMinusOneStone)
            return true;
        return lastRowPMinus1 < lastRowP1;
    }

    /**
     * zaehlt die Punkte der beiden Spieler und gibt das Ergebnis aus
     */
    private static void zaehlePunkte() {
        int p1Points = 0;
        int pMinus1Points = 0;
        for (int i = 0; i < spielfeld.length; i++) {
            if (i <= 3) {
                pMinus1Points += getPoints(i) * steineInReihe(-1, i);
                p1Points -= getPoints(i) * steineInReihe(1, i);
            } else {
                p1Points += getPoints(i) * steineInReihe(1, i);
                pMinus1Points -= getPoints(i) * steineInReihe(-1, i);
            }
        }

        p1Points += stonesInTarget.getAll(s -> s > 0).size() * 5;
        pMinus1Points += stonesInTarget.getAll(s -> s < 0).size() * 5;

        write("Spieler 1 hat " + p1Points + " Punkte.\nSpieler -1 hat: " + pMinus1Points + " Punkte.");
        points[0] = p1Points;
        points[1] = pMinus1Points; // weil ich keine R�ckgabewerte in dieser
        // Funktion verwenden darf
    }

    private static int getPoints(int row) {
        if (row == 0 || row == 7)
            return 5;
        if (row == 1 || row == 6)
            return 3;
        if (row == 2 || row == 5)
            return 2;
        if (row == 3 || row == 4)
            return 1;
        return -1;
    }

    /**
     * Spielablauf entsprechend Anfangszug, Folgezug, Bonuszug
     *
     * @param spieler ist 1 (Spielsteine 1 bis 12) oder -1 (Spielsteine -1 bis
     * -12)
     */
    private static void spielerZieht(int spieler, boolean bonusRule) {
        int nextMove = getAndMoveFigure(spieler, 1,
                "Spieler " + spieler + " ist am Zug.\n\nWelcher Stein soll bewegt werden?");
        if (nextMove != 0) {
            System.out.println(output());
            getAndMoveFigure(spieler, nextMove,
                    "Welcher Stein soll f�r den Folgezug mit einer Weite von " + nextMove + " benutzt werden?");
        }

        if (bonusMove) {
            if (spielende())
                return;
            System.out.println(output());
            while (true) {
                int stein = readInt("Welcher Stein soll f�r den Bonuszug benutzt werden?");
                boolean stoneInTarget = stonesInTarget.contains(stein);

                if ((!bonusRule || bonusRule && stoneInTarget) && !gueltigeEingabe(stein, spieler)) {
                    if (!bonusRule)
                        write("Der Stein " + stein + " geh�rt nicht zu Spieler " + spieler + ".");
                    else
                        write("Der Stein " + stein
                                + " geh�rt dem Gegenspieler und ist schon im Ziel und kann damit nicht versetzt werden.");
                    continue;
                }

                boolean forward;
                if (stoneInTarget) {
                    write("Da dieser Stein bereits auf der Ziellinie ist, kann er nur zur�ck bewegt werden.");
                    forward = false;
                } else {
                    forward = readBoolean("Vorw�rts (1) oder r�ckw�rts (0) ?");
                }

                int setzeZugResult = setzeZug(stein, 1, forward);
                if (setzeZugResult == -1)
                    continue;

                break;
            }
            bonusMove = false;
        }
    }

    /**
     *
     * @return die Weite f�r den n�chsten Zug (wenn er denn stattfindet)
     */
    private static int getAndMoveFigure(int spieler, int length, String text) {
        while (true) {
            int stein = readInt(text);
            if (!gueltigeEingabe(stein, spieler)) {
                write("Der Stein " + stein + " geh�rt nicht zu Spieler " + spieler + ".");
                continue;
            }

            int nextMove = setzeZug(stein, length, true);
            if (nextMove == -1) {
                continue;
            }

            return nextMove;
        }
    }

    private static boolean readBoolean(String text) {
        while (true) {
            int i = readInt(text);
            if (i != 0 && i != 1) {
                write("Als Eingabe ist nur 0 oder 1 erlaubt.");
                continue;
            }
            ;
            return i == 1;
        }
    }

    public static void main(String args[]) {
        int currentPlayer = randomPlayer();

        while (true) {
            boolean bonusRule = readBoolean("Wollen Sie mit (1) oder ohne (0) der Bonusregel spielen?");

            initSpiel();
            while (!spielende()) {
                System.out.println(output());
                spielerZieht(currentPlayer, bonusRule);
                currentPlayer *= -1;
            }
            System.out.println(output());
            zaehlePunkte();

            write("Damit hat Spieler " + determineWinner() + " gewonnen.");
            if (readBoolean("Wollen Sie nochmal spielen?")) {
                currentPlayer = determineWinner() * -1;
            } else {
                break;
            }
        }
    }

    private static int randomPlayer() {
        return dice() <= 3 ? 1 : -1;
    }

    private static int determineWinner() {
        return points[0] > points[1] ? 1 : -1;
    }
}
